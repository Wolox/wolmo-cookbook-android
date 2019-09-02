package ar.com.wolox.android.cookbook.camerax

import android.view.Display
import android.view.Surface

/** Helper function that gets the rotation of a [Display] in degrees */
fun Display.getRotationInDegrees() = when (rotation) {
    Surface.ROTATION_0 -> 0
    Surface.ROTATION_90 -> 90
    Surface.ROTATION_180 -> 180
    Surface.ROTATION_270 -> 270
    else -> null
}