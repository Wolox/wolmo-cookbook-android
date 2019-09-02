package ar.com.wolox.android.cookbook.camerax

import android.graphics.Matrix
import android.hardware.display.DisplayManager
import android.util.Size
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.Preview
import androidx.camera.core.Preview.OnPreviewOutputUpdateListener
import java.util.Objects
import kotlin.math.roundToInt

class AutoFitPreview(configuration: CameraWrapperConfiguration) : Preview(configuration.preview) {

    /** Internal variable used to keep track of the use case's output rotation */
    private var bufferRotation: Int = 0

    /** Internal variable used to keep track of the view's rotation */
    private var viewFinderRotation: Int? = null

    /** Internal variable used to keep track of the use-case's output dimension */
    private var bufferDimens: Size = Size(0, 0)

    /** Internal variable used to keep track of the view's dimension */
    private var viewFinderDimens: Size = Size(0, 0)

    /** Internal variable used to keep track of the view's display */
    private var viewFinderDisplay: Int = -1

    /** Internal reference of the [DisplayManager] */
    private lateinit var displayManager: DisplayManager

    private val onPreviewUpdate = OnPreviewOutputUpdateListener {
        val viewFinder = configuration.viewFinder.get() ?: return@OnPreviewOutputUpdateListener

        // To update the SurfaceTexture, we have to remove it and re-add it
        val parent = viewFinder.parent as ViewGroup
        parent.removeView(viewFinder)
        parent.addView(viewFinder, 0)

        // Update internal texture
        viewFinder.surfaceTexture = it.surfaceTexture

        // Apply relevant transformations
        bufferRotation = it.rotationDegrees
        updateTransform(viewFinder, viewFinder.display.getRotationInDegrees(), it.textureSize, viewFinderDimens)
    }

    private val onLayoutChange = View.OnLayoutChangeListener { view, left, top, right, bottom, _, _, _, _ ->
        val viewFinder = view as TextureView
        val newViewFinderDimens = Size(right - left, bottom - top)
        val rotation = viewFinder.display.getRotationInDegrees()
        updateTransform(viewFinder, rotation, bufferDimens, newViewFinderDimens)
    }

    init {
        // Make sure that the view finder reference is valid
        val viewFinder = configuration.viewFinder.get() ?: throw IllegalArgumentException("Invalid reference to view finder used")

        // Initialize the display and rotation from texture view information
        viewFinderDisplay = viewFinder.display.displayId
        viewFinderRotation = viewFinder.display.getRotationInDegrees()

        // Every time the view finder is updated, recompute layout
        onPreviewOutputUpdateListener = onPreviewUpdate

        // Every time the provided texture view changes, recompute layout
        viewFinder.addOnLayoutChangeListener(onLayoutChange)
    }

    /** Helper function that fits a camera preview into the given [TextureView] */
    private fun updateTransform(
        textureView: TextureView,
        rotation: Int?,
        newBufferDimens: Size,
        newViewFinderDimens: Size
    ) {
        when {
            rotation == viewFinderRotation &&
                    Objects.equals(newBufferDimens, bufferDimens) &&
                    Objects.equals(newViewFinderDimens, viewFinderDimens) -> {
                // Nothing has changed, no need to transform output again
                return
            }
            rotation == null -> {
                // Invalid rotation - wait for valid inputs before setting matrix
                return
            }
            newBufferDimens.width == 0 || newBufferDimens.height == 0 -> {
                // Invalid buffer dimens - wait for valid inputs before setting matrix
                return
            }
            newViewFinderDimens.width == 0 || newViewFinderDimens.height == 0 -> {
                // Invalid view finder dimens - wait for valid inputs before setting matrix
                return
            }
        }

        // Update actual preview data
        viewFinderRotation = rotation
        bufferDimens = newBufferDimens
        viewFinderDimens = newViewFinderDimens

        // Compute the center of the view finder
        val centerX = viewFinderDimens.width / 2f
        val centerY = viewFinderDimens.height / 2f

        // Buffers are rotated relative to the device's 'natural' orientation: swap width and height
        val bufferRatio = bufferDimens.height / bufferDimens.width.toFloat()

        val scaledWidth: Int
        val scaledHeight: Int
        // Match longest sides together -- i.e. apply center-crop transformation
        if (viewFinderDimens.width > viewFinderDimens.height) {
            scaledHeight = viewFinderDimens.width
            scaledWidth = (viewFinderDimens.width * bufferRatio).roundToInt()
        } else {
            scaledHeight = viewFinderDimens.height
            scaledWidth = (viewFinderDimens.height * bufferRatio).roundToInt()
        }

        // Compute the relative scale value
        val xScale = scaledWidth / viewFinderDimens.width.toFloat()
        val yScale = scaledHeight / viewFinderDimens.height.toFloat()

        val matrix = Matrix().apply {
            // Correct preview output to account for display rotation
            postRotate(-viewFinderRotation!!.toFloat(), centerX, centerY)

            // Scale input buffers to fill the view finder
            preScale(xScale, yScale, centerX, centerY)
        }

        // Finally, apply transformations to our TextureView
        textureView.setTransform(matrix)
    }
}
