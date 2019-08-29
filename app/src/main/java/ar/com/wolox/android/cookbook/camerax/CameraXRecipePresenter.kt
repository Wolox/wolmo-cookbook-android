package ar.com.wolox.android.cookbook.camerax

import android.graphics.SurfaceTexture
import android.util.DisplayMetrics
import android.util.Log
import androidx.camera.core.CameraX
import androidx.camera.core.ImageCapture
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import java.io.File
import javax.inject.Inject

class CameraXRecipePresenter @Inject constructor(private val cameraX: MyCameraX, private val filesHelper: FilesHelper) : BasePresenter<CameraXRecipeView>() {

    private var lens = CameraX.LensFacing.BACK

    override fun onViewAttached() {
        view.requestCameraPermissions()
    }

    fun onCameraPermissionGranted(displayMetrics: DisplayMetrics) = with(view) {
        cameraX.initialize(displayMetrics, MyCameraXConfiguration(lens = lens), object : MyCameraXUserListener {

            override fun onPreviewUpdate(surfaceTexture: SurfaceTexture) = updateCamera(surfaceTexture)

            override fun onLifecycleOwnerRequest() = view
        })

        enableShutter()

        if (cameraX.isLensAvailable(CameraX.LensFacing.FRONT)) {
            enableFlipButton()
        }
    }

    fun onCameraPermissionDenied() = view.showCameraError()

    fun onShutterClicked() {
        cameraX.takePicture(File(filesHelper.getNewCachePictureName()), object : ImageCapture.OnImageSavedListener {
            override fun onImageSaved(file: File) {
                Log.d("DylanLog", "Images saved: ${file.absolutePath}")
            }

            override fun onError(useCaseError: ImageCapture.UseCaseError, message: String, cause: Throwable?) {
                Log.d("DylanLog", "Error")
            }
        })
    }

    fun onFlipButtonClicked() {
        lens = if (CameraX.LensFacing.FRONT == lens) {
            CameraX.LensFacing.BACK
        } else {
            CameraX.LensFacing.FRONT
        }

        cameraX.changeLens(lens)
    }
}