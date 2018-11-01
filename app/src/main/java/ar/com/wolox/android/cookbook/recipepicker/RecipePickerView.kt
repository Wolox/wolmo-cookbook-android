package ar.com.wolox.android.cookbook.recipepicker

interface RecipePickerView {

    fun showRecipes(recipes: List<Recipe>)

    // TODO: Delete this method when adding real recipes
    fun goToBlankRecipe()

    fun goToGoogleLogin()

    fun goToNavigation()

    fun goToDataSyncRecipe()
}