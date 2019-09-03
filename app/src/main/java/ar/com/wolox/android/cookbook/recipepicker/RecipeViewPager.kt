package ar.com.wolox.android.cookbook.recipepicker

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.viewpager.widget.PagerAdapter
import android.view.View
import ar.com.wolox.android.cookbook.R
import kotlinx.android.synthetic.main.item_recipe_image.view.*

class RecipeViewPager(
    private val recipeItems: List<RecipeItem>,
    private val onRecipeClicked: (Recipe) -> Unit
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_recipe_image, null)
        return view.apply {
            val recipeItem = recipeItems[position]
            vItemRecipeImage.setImageResource(recipeItem.imageResId)
            vItemRecipeDescription.setText(recipeItem.stringResId)
            container.addView(this)
            setOnClickListener { onRecipeClicked(recipeItem.recipe) }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, viewObject: Any) {
        container.removeView(viewObject as View)
    }

    override fun getCount() = recipeItems.size

    override fun isViewFromObject(view: View, viewObject: Any) = view === viewObject
}