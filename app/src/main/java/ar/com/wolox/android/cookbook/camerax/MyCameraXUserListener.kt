package ar.com.wolox.android.cookbook.camerax

import android.graphics.SurfaceTexture
import androidx.lifecycle.LifecycleOwner

interface MyCameraXUserListener {

    fun onPreviewUpdate(surfaceTexture: SurfaceTexture)

    fun onLifecycleOwnerRequest(): LifecycleOwner
}