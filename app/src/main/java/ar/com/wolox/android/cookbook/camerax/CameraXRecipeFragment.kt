package ar.com.wolox.android.cookbook.camerax

import android.Manifest
import android.graphics.SurfaceTexture
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

    private val permissionListener = object : PermissionListener() {

        override fun onPermissionsDenied(deniedPermissions: Array<out String>) = presenter.onCameraPermissionDenied()

        override fun onPermissionsGranted() {
            vCamera.post {
                presenter.onCameraPermissionGranted(vCamera)
            }
        }
    }

    override fun layout() = R.layout.fragment_camerax

    override fun init() {
    }

    override fun setListeners() {
        vShutter.setOnClickListener { presenter.onShutterClicked() }
        vFlipButton.setOnClickListener { presenter.onFlipButtonClicked() }
    }

    override fun requestCameraPermissions() {
        permissionManager.requestPermission(this, permissionListener, Manifest.permission.CAMERA)
    }

    override fun showPermissionsError() = toastFactory.show(R.string.camerax_permissions_denied)

    override fun updateCamera(surfaceTexture: SurfaceTexture) {
        with(vCamera.parent as ViewGroup) {
            removeView(vCamera)
            addView(vCamera, 0)
        }

        vCamera.surfaceTexture = surfaceTexture
    }

    override fun enableShutter() {
        vShutter.isEnabled = true
    }

    override fun enableFlipButton() {
        vFlipButton.isEnabled = true
    }

    override fun goToShowPicture(file: String) = PictureActivity.start(requireContext(), file)

    override fun showError(message: String) = toastFactory.show(message)
}

interface CameraXRecipeView : LifecycleOwner {

    /** Request permissions for showing the camera and take pictures. */
    fun requestCameraPermissions()

    /** Show an error informing permissions were denied. */
    fun showPermissionsError()

    /** Update camera texture by a [surfaceTexture]. */
    fun updateCamera(surfaceTexture: SurfaceTexture)

    /** Enable shutter button for allowing taking picture. */
    fun enableShutter()

    /** Enable flip button to flip camera. */
    fun enableFlipButton()

    /** Show error [message]. */
    fun showError(message: String)

    /** Redirect to image [file] viewer. */
    fun goToShowPicture(file: String)
}