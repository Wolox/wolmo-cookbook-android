package ar.com.wolox.android.cookbook.navigation

import android.support.v4.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import ar.com.wolox.android.cookbook.R

fun Fragment.requireNavController() = Navigation.findNavController(view!!)

fun createOptionsWithDefaultAnimations(): NavOptions {
    return NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()
}

fun getBackwardOptions(destination: Int, inclusive: Boolean): NavOptions {
    return NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_left)
            .setPopUpTo(destination, inclusive)
            .build()
}