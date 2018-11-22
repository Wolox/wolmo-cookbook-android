package ar.com.wolox.android.cookbook.recipepicker

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class RecipePickerPresenter @Inject constructor() : BasePresenter<RecipePickerView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        view.showRecipes(Recipe.values().toList())
    }

    fun onRecipeClicked(clickedRecipe: Recipe) {
        // TODO: Take to corresponding recipe flow
        when (clickedRecipe) {
            Recipe.YAWN_CAT -> view.goToBlankRecipe()
            Recipe.HIGH_CAT -> view.goToBlankRecipe()
            Recipe.SURPRISED_CAT -> view.goToBlankRecipe()
            Recipe.COOL_CAT -> view.goToBlankRecipe()
            Recipe.GOOGLE_LOGIN -> view.goToGoogleLogin()
            Recipe.NAVIGATION -> view.goToNavigation()
        }
    }
}
