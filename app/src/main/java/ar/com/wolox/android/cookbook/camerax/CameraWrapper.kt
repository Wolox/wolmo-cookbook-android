package ar.com.wolox.android.cookbook.camerax

import android.annotation.SuppressLint
import androidx.camera.core.CameraInfoUnavailableException
import androidx.camera.core.CameraX
import androidx.camera.core.ImageCapture
import androidx.lifecycle.LifecycleOwner
import java.io.File
import javax.inject.Inject

/** Camera wrapper for new CameraX library which is still on alpha stage. */
class CameraWrapper @Inject constructor() {

    /** Listener that should be implemented by the class where the camera is used. */
    lateinit var listener: CameraWrapperListener

    private lateinit var configuration: CameraWrapperConfiguration
    private lateinit var preview: AutoFitPreview
    private lateinit var imageCapture: ImageCapture

    /**
     * Bind the camera to a [lifecycleOwner].
     * It means the startup, shutdown and production of camera data are governed by that Android Architecture Lifecycle.
     */
    private fun bindToLifecycle(lifecycleOwner: LifecycleOwner) = CameraX.bindToLifecycle(lifecycleOwner, preview, imageCapture)

    /** Restart the camera with a specific [configuration], it's important to unbind all the use cases and the start it again. */
    fun restart(myCameraXConfiguration: CameraWrapperConfiguration) {
        CameraX.unbindAll()
        start(myCameraXConfiguration)
    }

    /** Start the camera with a specific [configuration]. It'll build a Preview and a Picture use cases. */
    fun start(configuration: CameraWrapperConfiguration) {

        this.configuration = configuration

        imageCapture = ImageCapture(configuration.imageCapture)
        preview = AutoFitPreview(configuration).apply {
            setOnPreviewOutputUpdateListener { listener.onPreviewUpdate(it.surfaceTexture) }
        }

        bindToLifecycle(listener.onLifecycleOwnerRequest())
    }

    /** Take a picture and export to a [file]. Once the capture is complete, the [onImageSavedListener] will be invoked. */
    fun takePicture(file: File, onImageSavedListener: ImageCapture.OnImageSavedListener) = imageCapture.takePicture(file, onImageSavedListener)

    /** Change lens if it's available to the indicated [lens]. It'll restart the camera. */
    fun changeLens(lens: CameraX.LensFacing) {
        if (isLensAvailable(lens)) {
            restart(configuration.copy(lens = lens))
        }
    }

    /** Returns true if the [lens] is available, false otherwise. */
    @SuppressLint("RestrictedApi")
    fun isLensAvailable(lens: CameraX.LensFacing) = try {
        CameraX.getCameraWithLensFacing(lens)
        true
    } catch (exception: CameraInfoUnavailableException) {
        false
    }
}