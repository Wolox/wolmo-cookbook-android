package ar.com.wolox.android.cookbook.recipepicker

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class RecipePickerPresenter @Inject constructor() : BasePresenter<RecipePickerView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        view.showRecipes(Recipe.values().toList())
    }

    fun onRecipeCLicked(clickedRecipe: Recipe) {
        // TODO: Take to corresponding recipe flow
    }
}
