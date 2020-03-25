package ar.com.wolox.android.cookbook.recipepicker

interface RecipePickerView {

    fun showRecipes(recipes: List<Recipe>)

    fun goToGoogleLogin()

    fun goToFacebookLogin()

    fun goToTwitterLogin()

    fun goToInstagramLogin()

    fun goToRoom()

    fun goToMpChart()

    fun goToNavigation()

    fun goToDataSyncRecipe()

    fun goToTests()

    fun goToKoin()

    fun goToCoroutines()

    fun goToNotificationsRecipe()

    fun goToGraphQlRecipe()

    fun goToAnalyticsRecipe()
}