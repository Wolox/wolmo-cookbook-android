package ar.com.wolox.android.cookbook.camerax

import android.annotation.SuppressLint
import android.util.Rational
import android.util.Size
import androidx.camera.core.CameraInfoUnavailableException
import androidx.camera.core.CameraX
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureConfig
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.lifecycle.LifecycleOwner
import java.io.File
import javax.inject.Inject

class MyCameraX @Inject constructor() {

    lateinit var listener: MyCameraXUserListener

    private lateinit var preview: Preview
    private lateinit var imageCapture: ImageCapture

    /**
     * Bind the camera to a [lifecycleOwner].
     * It means the startup, shutdown and production of camera data are governed by that Android Architecture Lifecycle.
     */
    private fun bindToLifecycle(lifecycleOwner: LifecycleOwner) = CameraX.bindToLifecycle(lifecycleOwner, preview, imageCapture)

    /** For restarting the camera, it's important to unbind all the use cases and the start it again. */
    fun restart(myCameraXConfiguration: MyCameraXConfiguration) {
        CameraX.unbindAll()
        start(myCameraXConfiguration)
    }

    /** Start the camera. It'll build a Preview and a Picture use cases. */
    fun start(configuration: MyCameraXConfiguration) {

        val previewConfig = PreviewConfig
                .Builder()
                .setLensFacing(configuration.lens)
                .setTargetAspectRatio(configuration.aspectRatio)
                .setTargetResolution(configuration.resolution)
                .build()

        val imageCaptureConfig = ImageCaptureConfig
                .Builder()
                .setLensFacing(configuration.lens)
                .setTargetAspectRatio(configuration.aspectRatio)
                .setTargetResolution(configuration.resolution)
                .build()

        preview = Preview(previewConfig).apply {
            setOnPreviewOutputUpdateListener { listener.onPreviewUpdate(it.surfaceTexture) }
        }
        imageCapture = ImageCapture(imageCaptureConfig)

        bindToLifecycle(listener.onLifecycleOwnerRequest())
    }

    /** Take a picture and export to a [file]. Once the capture is complete, the [onImageSavedListener] will be invoked. */
    fun takePicture(file: File, onImageSavedListener: ImageCapture.OnImageSavedListener) = imageCapture.takePicture(file, onImageSavedListener)

    /** Change lens if it's available to the indicated [lens]. It'll restart the camera. */
    fun changeLens(lens: CameraX.LensFacing) {
        if (isLensAvailable(lens)) {
            restart(MyCameraXConfiguration(lens = lens))
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