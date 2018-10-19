package ar.com.wolox.android.cookbook.recipepicker

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_recipe_picker.*

class RecipePickerFragment : WolmoFragment<RecipePickerPresenter>() {

    override fun layout() = R.layout.fragment_recipe_picker

    override fun init() {
        vRecipePickerSelectionViewPager.apply {
            adapter = RecipeViewPager(intArrayOf(
                R.drawable.bg_yawning_cat, R.drawable.bg_falopa_cat, R.drawable.bg_sunglasses_cat,
                R.drawable.bg_surprised_cat
            ))
            setPageTransformer(false, CarouselEffectTransformer())
            pageMargin = resources.getDimensionPixelSize(R.dimen.spacing_medium_more)
        }
    }

    companion object {
        fun newInstance() = RecipePickerFragment()
    }
}