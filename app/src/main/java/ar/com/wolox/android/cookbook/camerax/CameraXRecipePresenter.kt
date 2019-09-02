package ar.com.wolox.android.cookbook.camerax

import android.graphics.SurfaceTexture
import android.view.TextureView
import androidx.camera.core.CameraX
import androidx.camera.core.ImageCapture
import androidx.lifecycle.LifecycleOwner
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import java.io.File
import java.lang.ref.WeakReference
import javax.inject.Inject

class CameraXRecipePresenter @Inject constructor(
    private val camera: CameraWrapper,
    private val filesHelper: FilesHelper
) : BasePresenter<CameraXRecipeView>(), CameraWrapperListener {

    private var lens = CameraX.LensFacing.BACK

    private fun enableUI() = with(view) {
        enableShutter()

        if (camera.isLensAvailable(CameraX.LensFacing.FRONT)) {
            enableFlipButton()
        }
    }

    private fun initializeCamera(textureView: TextureView) {
        camera.listener = this
        val configuration = CameraWrapperConfiguration(
                viewFinder = WeakReference(textureView),
                rotation = textureView.display.rotation,
                lens = lens)
        camera.start(configuration)
    }

    override fun onViewAttached() {
        view.requestCameraPermissions()
    }

    override fun onPreviewUpdate(surfaceTexture: SurfaceTexture) = view.updateCamera(surfaceTexture)

    override fun onLifecycleOwnerRequest(): LifecycleOwner = view

    /** Invoked when camera permissions are granted by user. */
    fun onCameraPermissionGranted(textureView: TextureView) {
        initializeCamera(textureView)
        enableUI()
    }

    /** Invoked when camera permissions are denied by user. */
    fun onCameraPermissionDenied() = view.showPermissionsError()

    /** Invoked when shutter button is clicked. */
    fun onShutterClicked() {
        camera.takePicture(File(filesHelper.getNewCachePictureName()), object : ImageCapture.OnImageSavedListener {

            override fun onImageSaved(file: File) = view.goToShowPicture(file.absolutePath)

            override fun onError(useCaseError: ImageCapture.UseCaseError, message: String, cause: Throwable?) = view.showError(message)
        })
    }

    /** Invoked on flip button is clicked. */
    fun onFlipButtonClicked() {
        lens = if (CameraX.LensFacing.FRONT == lens) CameraX.LensFacing.BACK else CameraX.LensFacing.FRONT
        camera.changeLens(lens)
    }
}