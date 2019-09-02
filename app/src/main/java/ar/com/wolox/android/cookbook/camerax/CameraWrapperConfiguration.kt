package ar.com.wolox.android.cookbook.camerax

import android.util.DisplayMetrics
import android.util.Rational
import android.view.TextureView
import androidx.camera.core.CameraX
import androidx.camera.core.ImageCaptureConfig
import androidx.camera.core.PreviewConfig
import java.lang.ref.WeakReference

/**
 * Configuration for uses cases by specifying the [viewFinder] where the camera will be displayed, the camera [rotation] and the selected [lens].
 * It doesn't use a resolution to let CameraX decide which'll be the best.
 */
data class CameraWrapperConfiguration(
    val viewFinder: WeakReference<TextureView>,
    val rotation: Int,
    val lens: CameraX.LensFacing = CameraX.LensFacing.BACK
) {

    private val displayMetrics = DisplayMetrics().also { viewFinder.get()!!.display.getMetrics(it) }

    /** Configuration for Preview use case. */
    val preview: PreviewConfig by lazy {
        PreviewConfig
                .Builder()
                .setTargetRotation(rotation)
                .setLensFacing(lens)
                .setTargetAspectRatio(Rational(displayMetrics.widthPixels, displayMetrics.heightPixels))
                .build()
    }

    /** Configuration for Image Capture use case. */
    val imageCapture: ImageCaptureConfig by lazy {
        ImageCaptureConfig
                .Builder()
                .setTargetRotation(rotation)
                .setLensFacing(lens)
                .setTargetAspectRatio(Rational(displayMetrics.widthPixels, displayMetrics.heightPixels))
                .build()
    }
}
