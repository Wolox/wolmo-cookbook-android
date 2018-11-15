package ar.com.wolox.android.cookbook.navigation

import androidx.navigation.NavOptions

class NavigationUtils {

    companion object {
        var mForwardEnterAnim: Int = 0
        var mForwardExitAnim: Int = 0
        var mForwardPopEnterAnim: Int = 0
        var mForwardPopExitAnim: Int = 0

        var mBackwardEnterAnim: Int = 0

        fun setForwardAnimationOptions(enterAnim: Int, exitAnim: Int, popEnterAnim: Int, popExitAnim: Int) {
            mForwardEnterAnim = enterAnim
            mForwardExitAnim = exitAnim
            mForwardPopEnterAnim = popEnterAnim
            mForwardPopExitAnim = popExitAnim
        }

        fun setBackwardAnimationOptions(enterAnim: Int) {
            mBackwardEnterAnim = enterAnim
        }

        fun getForwardAnimationOptions(): NavOptions = NavOptions.Builder()
                .setEnterAnim(mForwardEnterAnim)
                .setExitAnim(mForwardExitAnim)
                .setPopEnterAnim(mForwardPopEnterAnim)
                .setPopExitAnim(mForwardPopExitAnim)
                .build()

        fun getBackwardOptions(destination: Int, inclusive: Boolean): NavOptions = NavOptions.Builder()
                .setEnterAnim(mBackwardEnterAnim)
                .setPopUpTo(destination, inclusive)
                .build()
    }
}
