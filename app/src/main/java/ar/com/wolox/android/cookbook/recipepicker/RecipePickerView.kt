package ar.com.wolox.android.cookbook.recipepicker

interface RecipePickerView {

    fun showRecipes(recipes: List<Recipe>)

    fun goToGoogleLogin()

    fun goToNavigation()

    fun goToDataSyncRecipe()
}