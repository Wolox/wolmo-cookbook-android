package ar.com.wolox.android.cookbook.recipepicker

interface RecipePickerView {

    fun showRecipes(recipes: List<Recipe>)

    fun goToGoogleLogin()

    fun goToFacebookLogin()

    fun goToNavigation()

    fun goToDataSyncRecipe()

    fun goToTests()

    fun goToKoin()

    fun goToNotificationsRecipe()

    fun goToScanQrRecipe()
}