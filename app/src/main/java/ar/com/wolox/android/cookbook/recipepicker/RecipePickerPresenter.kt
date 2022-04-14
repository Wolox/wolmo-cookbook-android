package ar.com.wolox.android.cookbook.recipepicker

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class RecipePickerPresenter @Inject constructor() : BasePresenter<RecipePickerView>(), RecipeClickListener {

    override fun onViewAttached() {
        super.onViewAttached()

        view?.showRecipes(Recipe.values().toList())
    }

    fun onSearchPerformed(search: String) {
        val recipes = Recipe.values()
        view?.showRecipes(recipes.filter { it.fullName.contains(search, true) })
    }

    override fun onRecipeClick(clickedRecipe: Recipe) {
        when (clickedRecipe) {
            Recipe.LOTTIE -> view?.goToLottieRecipe()
            Recipe.MERCADOPAGO -> view?.goToMercadoPagoRecipe()
            Recipe.ANALYTICS -> view?.goToAnalyticsRecipe()
            Recipe.GOOGLE_LOGIN -> view?.goToGoogleLogin()
            Recipe.FACEBOOK_LOGIN -> view?.goToFacebookLogin()
            Recipe.TWITTER_LOGIN -> view?.goToTwitterLogin()
            Recipe.INSTAGRAM_LOGIN -> view?.goToInstagramLogin()
            Recipe.ROOM -> view?.goToRoom()
            Recipe.MP_CHART -> view?.goToMpChart()
            Recipe.NAVIGATION -> view?.goToNavigation()
            Recipe.DATA_SYNC -> view?.goToDataSyncRecipe()
            Recipe.TESTS -> view?.goToTests()
            Recipe.KOIN -> view?.goToKoin()
            Recipe.COROUTINES -> view?.goToCoroutines()
            Recipe.NOTIFICATIONS -> view?.goToNotificationsRecipe()
            Recipe.GRAPH_QL -> view?.goToGraphQlRecipe()
            Recipe.BIOMETRIC_LOGIN -> view?.goToFingerprintRecipe()
            Recipe.MAP -> view?.goToMaps()
            Recipe.ANIMATED_INPUT -> view?.goToAnimatedInput()
            Recipe.BOUNCE_EFFECT -> view?.goToBounceEffect()
        }
    }
}
