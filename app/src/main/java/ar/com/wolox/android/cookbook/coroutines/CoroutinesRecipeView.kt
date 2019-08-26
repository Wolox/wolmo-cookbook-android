package ar.com.wolox.android.cookbook.coroutines

interface CoroutinesRecipeView {

    fun showLoginError()

    fun showEmptyEmailError()

    fun showInvalidEmailError()

    fun showEmptyPasswordError()

    fun showWelcomeMessage(name: String)
}
