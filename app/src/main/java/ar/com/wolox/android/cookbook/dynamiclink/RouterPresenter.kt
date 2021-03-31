package ar.com.wolox.android.cookbook.dynamiclink

import ar.com.wolox.essen.recipe.RecipeRepository
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class RouterPresenter @Inject constructor(
        private val recipeRepository: RecipeRepository
) : BasePresenter<RouterView>() {

    fun onRecipeFromLink(externalId: Int) {
        recipeRepository.setDetailingRecipeExternalId(externalId)
        view.goToRecipe()
    }
}