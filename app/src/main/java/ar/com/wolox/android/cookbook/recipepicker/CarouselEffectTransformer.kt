package ar.com.wolox.android.cookbook.recipepicker

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.view.View
import ar.com.wolox.android.cookbook.R

class CarouselEffectTransformer : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val viewPager = view.parent as ViewPager
        val leftInScreen = view.left - viewPager.scrollX
        val centerXInViewPager = leftInScreen + view.measuredWidth / 2
        val offsetX = centerXInViewPager - viewPager.measuredWidth / 2
        val offsetRate = offsetX.toFloat() * 0.38f / viewPager.measuredWidth
        val scaleFactor = 1 - Math.abs(offsetRate)

        if (scaleFactor > 0) {
            view.scaleX = scaleFactor
            view.scaleY = scaleFactor
            view.translationX = -viewPager.context.resources.getDimension(R.dimen.carousel_effect_max_translate_x) * offsetRate
        }

        ViewCompat.setElevation(view, scaleFactor)
    }
}