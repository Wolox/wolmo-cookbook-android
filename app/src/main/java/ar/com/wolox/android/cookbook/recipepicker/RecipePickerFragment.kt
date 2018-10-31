package ar.com.wolox.android.cookbook.recipepicker

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.googleLogin.GoogleLoginActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_recipe_picker.*

class RecipePickerFragment : WolmoFragment<RecipePickerPresenter>(), RecipePickerView {

    override fun layout() = R.layout.fragment_recipe_picker

    override fun init() {}

    override fun showRecipes(recipes: List<Recipe>) {
        vRecipePickerSelectionViewPager.apply {
            adapter = RecipeViewPager(mapRecipesToItems(recipes)) {
                presenter.onRecipeClicked(it)
            }
            setPageTransformer(false, CarouselEffectTransformer())
            pageMargin = resources.getDimensionPixelSize(R.dimen.spacing_medium_more)
        }
    }

    private fun mapRecipesToItems(recipes: List<Recipe>): List<RecipeItem> {
        // Create a RecipeItem with the desired image & text for it inside the 'when' statement
        return recipes.map {
            when (it) {
                Recipe.YAWN_CAT -> RecipeItem(it, R.drawable.bg_yawning_cat, R.string.recipe_picker_yawn_cat)
                Recipe.HIGH_CAT -> RecipeItem(it, R.drawable.bg_high_cat, R.string.recipe_picker_high_cat)
                Recipe.COOL_CAT -> RecipeItem(it, R.drawable.bg_cool_cat, R.string.recipe_picker_cool_cat)
                Recipe.SURPRISED_CAT -> RecipeItem(it, R.drawable.bg_surprised_cat, R.string.recipe_picker_surprised_cat)
                Recipe.GOOGLE_LOGIN -> RecipeItem(it, R.drawable.google_login, R.string.recipe_picker_google_login)
            }
        }
    }

    override fun goToBlankRecipe() {
        requireContext().startActivity(Intent(requireContext(), BlankActivity::class.java))
    }

    override fun goToGoogleLogin() {
        requireActivity().startActivity(Intent(requireContext(), GoogleLoginActivity::class.java))
    }

    companion object {
        fun newInstance() = RecipePickerFragment()
    }
}