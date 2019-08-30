package ar.com.wolox.android.cookbook.camerax

import android.util.Rational
import androidx.camera.core.CameraX
import androidx.camera.core.ImageCaptureConfig
import androidx.camera.core.PreviewConfig

/**
 * Configuration for uses cases by specifying [rotation], [aspectRatio] and [lens].
 * It doesn't use a resolution to let CameraX decide which'll be the best.
 */
data class CameraWrapperConfiguration(
    private val rotation: Int,
    private val aspectRatio: Rational,
    private val lens: CameraX.LensFacing = CameraX.LensFacing.BACK
) {

    /** Configuration for Preview use case. */
    val preview: PreviewConfig by lazy {
        PreviewConfig
                .Builder()
                .setTargetRotation(rotation)
                .setLensFacing(lens)
                .setTargetAspectRatio(aspectRatio)
                .build()
    }

    /** Configuration for Image Capture use case. */
    val imageCapture: ImageCaptureConfig by lazy {
        ImageCaptureConfig
                .Builder()
                .setTargetRotation(rotation)
                .setLensFacing(lens)
                .setTargetAspectRatio(aspectRatio)
                .build()
    }
}
