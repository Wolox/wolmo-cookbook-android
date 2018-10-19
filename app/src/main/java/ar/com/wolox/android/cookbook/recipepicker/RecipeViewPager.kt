package ar.com.wolox.android.cookbook.recipepicker

import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v4.view.PagerAdapter
import android.view.View
import ar.com.wolox.android.cookbook.R
import kotlinx.android.synthetic.main.item_recipe_image.view.*

class RecipeViewPager(private val imageResources: IntArray) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_recipe_image, null)
        return view.apply {
            vItemRecipeImage.setImageResource(imageResources[position])
            container.addView(this)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, viewObject: Any) {
        container.removeView(viewObject as View)
    }

    override fun getCount() = imageResources.size

    override fun isViewFromObject(view: View, viewObject: Any) = view === viewObject
}