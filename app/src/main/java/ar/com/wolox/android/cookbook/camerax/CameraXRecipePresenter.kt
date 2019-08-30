package ar.com.wolox.android.cookbook.camerax

import android.graphics.SurfaceTexture
import android.util.DisplayMetrics
import android.util.Rational
import android.view.Display
import androidx.camera.core.CameraX
import androidx.camera.core.ImageCapture
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import java.io.File
import javax.inject.Inject

class CameraXRecipePresenter @Inject constructor(private val cameraX: MyCameraX, private val filesHelper: FilesHelper) : BasePresenter<CameraXRecipeView>() {

    private var lens = CameraX.LensFacing.BACK

    private fun enableUI() = with(view) {
        enableShutter()

        if (cameraX.isLensAvailable(CameraX.LensFacing.FRONT)) {
            enableFlipButton()
        }
    }

    private fun initializeCamera(display: Display) {
        cameraX.listener = object : MyCameraXUserListener {

            override fun onPreviewUpdate(surfaceTexture: SurfaceTexture) = view.updateCamera(surfaceTexture)

            override fun onLifecycleOwnerRequest() = view
        }

        val displayMetrics = DisplayMetrics().also { display.getRealMetrics(it) }
        val configuration = MyCameraXConfiguration(
                rotation = display.rotation,
                aspectRatio = Rational(displayMetrics.widthPixels, displayMetrics.heightPixels),
                lens = lens)
        cameraX.start(configuration)
    }

    override fun onViewAttached() {
        view.requestCameraPermissions()
    }

    /** Invoked when camera permissions are granted by user. */
    fun onCameraPermissionGranted(display: Display) {
        initializeCamera(display)
        enableUI()
    }

    /** Invoked when camera permissions are denied by user. */
    fun onCameraPermissionDenied() = view.showPermissionsError()

    /** Invoked when shutter button is clicked. */
    fun onShutterClicked() {
        cameraX.takePicture(File(filesHelper.getNewCachePictureName()), object : ImageCapture.OnImageSavedListener {

            override fun onImageSaved(file: File) = view.showImage(file)

            override fun onError(useCaseError: ImageCapture.UseCaseError, message: String, cause: Throwable?) = view.showError(message)
        })
    }

    /** Invoked on flip button is clicked. */
    fun onFlipButtonClicked() {
        lens = if (CameraX.LensFacing.FRONT == lens) CameraX.LensFacing.BACK else CameraX.LensFacing.FRONT
        cameraX.changeLens(lens)
    }
}