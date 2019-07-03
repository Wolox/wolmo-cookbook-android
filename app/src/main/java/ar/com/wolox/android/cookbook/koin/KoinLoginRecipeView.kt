package ar.com.wolox.android.cookbook.koin

interface KoinLoginRecipeView {

    fun goToNextWindow()

    fun showLoginError()

    fun showEmptyEmailError()

    fun showInvalidEmailError()

    fun showEmptyPasswordError()
}
