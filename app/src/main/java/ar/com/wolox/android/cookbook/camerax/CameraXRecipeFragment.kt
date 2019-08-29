package ar.com.wolox.android.cookbook.camerax

import android.Manifest
import android.graphics.Matrix
import android.graphics.SurfaceTexture
import android.util.DisplayMetrics
import android.view.Surface
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.permission.PermissionListener
import ar.com.wolox.wolmo.core.permission.PermissionManager
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_camerax.*
import javax.inject.Inject

class CameraXRecipeFragment : WolmoFragment<CameraXRecipePresenter>(), CameraXRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    @Inject
    internal lateinit var permissionManager: PermissionManager

    override fun layout() = R.layout.fragment_camerax

    override fun init() {
    }

    override fun setListeners() {
        vShutter.setOnClickListener { presenter.onShutterClicked() }
        vFlipButton.setOnClickListener { presenter.onFlipButtonClicked() }
    }

    override fun requestCameraPermissions() {
        permissionManager.requestPermission(this, object : PermissionListener() {
            override fun onPermissionsDenied(deniedPermissions: Array<out String>) {
                presenter.onCameraPermissionDenied()
            }

            override fun onPermissionsGranted() {
                vCamera.post {
                    presenter.onCameraPermissionGranted(DisplayMetrics().also { vCamera.display.getRealMetrics(it) })
                }
            }
        }, Manifest.permission.CAMERA)
    }

    override fun showCameraError() = toastFactory.show(R.string.camerax_permissions_denied)

    override fun updateCamera(surfaceTexture: SurfaceTexture) {
        // To update the SurfaceTexture, we have to remove it and re-add it
        with(vCamera.parent as ViewGroup) {
            removeView(vCamera)
            addView(vCamera, 0)
        }

        vCamera.surfaceTexture = surfaceTexture
    }

    override fun updateTransformation() {
        val matrix = Matrix()

        // Compute the center of the view finder
        val centerX = vCamera.width / 2f
        val centerY = vCamera.height / 2f

        // Correct preview output to account for display rotation
        val rotationDegrees = when (vCamera.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        // Finally, apply transformations to our TextureView
        vCamera.setTransform(matrix)
    }

    override fun enableShutter() {
        vShutter.isEnabled = true
    }

    override fun enableFlipButton() {
        vFlipButton.isEnabled = true
    }
}

interface CameraXRecipeView : LifecycleOwner {

    fun requestCameraPermissions()

    fun showCameraError()

    fun updateCamera(surfaceTexture: SurfaceTexture)

    fun updateTransformation()

    fun enableShutter()

    fun enableFlipButton()
}