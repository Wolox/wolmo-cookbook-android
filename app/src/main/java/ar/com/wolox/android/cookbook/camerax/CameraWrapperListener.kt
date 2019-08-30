package ar.com.wolox.android.cookbook.camerax

import android.graphics.SurfaceTexture
import androidx.lifecycle.LifecycleOwner

/** Listener to be implemented where camera is used. */
interface CameraWrapperListener {

    /** Invoked on camera preview [surfaceTexture] is updated. */
    fun onPreviewUpdate(surfaceTexture: SurfaceTexture)

    /** Invoked when a [LifecycleOwner] is requested. */
    fun onLifecycleOwnerRequest(): LifecycleOwner
}