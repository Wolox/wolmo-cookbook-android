package ar.com.wolox.android.cookbook.navigation.screens

import ar.com.wolox.android.cookbook.R
import me.aartikov.alligator.Screen
import me.aartikov.alligator.TransitionType
import me.aartikov.alligator.animations.AnimationData
import me.aartikov.alligator.animations.SimpleTransitionAnimation
import me.aartikov.alligator.animations.TransitionAnimation
import me.aartikov.alligator.animations.providers.TransitionAnimationProvider

class NavigationAnimationTransitionProvider : TransitionAnimationProvider {
    override fun getAnimation(
        transitionType: TransitionType,
        screenClassFrom: Class<out Screen>,
        screenClassTo: Class<out Screen>,
        isActivity: Boolean,
        animationData: AnimationData?
    ): TransitionAnimation =
            when (transitionType) {
            TransitionType.FORWARD -> SimpleTransitionAnimation(R.anim.slide_in_right, R.anim.slide_out_left)
            TransitionType.BACK -> SimpleTransitionAnimation(R.anim.slide_in_left, R.anim.slide_out_right)
            else -> TransitionAnimation.DEFAULT
        }
}
