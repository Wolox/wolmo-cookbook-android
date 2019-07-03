package ar.com.wolox.android.cookbook.koin

import ar.com.wolox.android.cookbook.koin.core.BasePresenter

class KoinLoginRecipePresenter(
    override var view: KoinLoginRecipeView,
    private val loginService: KoinLoginRecipeService
) : BasePresenter<KoinLoginRecipeView> {

    fun onLoginButtonClick(email: String, password: String) {
        when {
            email.isEmpty() -> view.showEmptyEmailError()
            !isValidEmail(email) -> view.showInvalidEmailError()
            password.isEmpty() -> view.showEmptyPasswordError()
            else -> loginService.login(email, password, { view.goToNextWindow() }, { view.showLoginError() })
        }
    }

    private fun isValidEmail(email: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}