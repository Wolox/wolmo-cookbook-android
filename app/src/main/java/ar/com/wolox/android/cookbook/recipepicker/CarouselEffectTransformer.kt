package ar.com.wolox.android.cookbook.recipepicker

import androidx.viewpager.widget.ViewPager
import android.view.View
import ar.com.wolox.android.cookbook.R
import kotlinx.android.synthetic.main.item_recipe_image.view.*
import kotlin.math.absoluteValue

/**
 * [android.support.v4.view.ViewPager.PageTransformer] that transform pages to give a Carousel
 * Effect. Heavily inspired by https://github.com/bhaveshjabuvani88/CarouselEffect.
 */
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

        view.vItemRecipeDescription.apply {
            alpha = if (position in 0.0f..0.32f) -6.25f*(position - 0.16f).absoluteValue + 1 else 0.0f
        }
    }
}