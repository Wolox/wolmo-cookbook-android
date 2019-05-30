package ar.com.wolox.android.cookbook.recipepicker

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class RecipePickerPresenter @Inject constructor() : BasePresenter<RecipePickerView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        view.showRecipes(Recipe.values().toList())
    }

    fun onRecipeClicked(clickedRecipe: Recipe) {
        when (clickedRecipe) {
            Recipe.GOOGLE_LOGIN -> view.goToGoogleLogin()
            Recipe.FACEBOOK_LOGIN -> view.goToFacebookLogin()
            Recipe.NAVIGATION -> view.goToNavigation()
            Recipe.DATA_SYNC -> view.goToDataSyncRecipe()
            Recipe.MOTION_LAYOUT -> view.goToMotionLayoutMenu()
        }
    }
}
