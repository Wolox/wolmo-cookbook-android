package ar.com.wolox.android.cookbook.camerax

import android.Manifest
import android.graphics.BitmapFactory
import android.graphics.SurfaceTexture
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.permission.PermissionListener
import ar.com.wolox.wolmo.core.permission.PermissionManager
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_camerax.*
import java.io.File
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
                presenter.onCameraPermissionGranted(vCamera.display)
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
        // To update the SurfaceTexture, we have to remove it and re-add it
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

    override fun showImage(file: File) {
        vImage.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
        vImage.visibility = View.VISIBLE
        vCloseImageButton.visibility = View.VISIBLE
    }

    override fun showError(message: String) = toastFactory.show(message)
}

interface CameraXRecipeView : LifecycleOwner {

    fun requestCameraPermissions()

    fun showPermissionsError()

    fun updateCamera(surfaceTexture: SurfaceTexture)

    fun enableShutter()

    fun enableFlipButton()

    fun showImage(file: File)

    fun showError(message: String)
}