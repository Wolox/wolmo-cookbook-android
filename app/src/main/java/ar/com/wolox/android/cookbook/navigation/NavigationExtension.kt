package ar.com.wolox.android.cookbook.navigation

import androidx.navigation.NavOptions
import ar.com.wolox.android.cookbook.R

class NavigationExtension {

    companion object {

        fun createOptionsWithDefaultAnimations(): NavOptions {
            return NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_in_right)
                    .setExitAnim(R.anim.slide_out_left)
                    .setPopEnterAnim(R.anim.slide_in_left)
                    .setPopExitAnim(R.anim.slide_out_right)
                    .build()
        }

        fun getBackwardOptions(destination: Int, inclusive: Boolean): NavOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_left)
                .setPopUpTo(destination, inclusive)
                .build()
    }
}
