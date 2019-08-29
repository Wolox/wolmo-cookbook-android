package ar.com.wolox.android.cookbook.camerax

import android.util.Rational
import android.util.Size
import androidx.camera.core.CameraX

data class MyCameraXConfiguration(
    val aspectRatio: Rational? = null,
    val resolution: Size? = null,
    val lens: CameraX.LensFacing = CameraX.LensFacing.BACK
)
