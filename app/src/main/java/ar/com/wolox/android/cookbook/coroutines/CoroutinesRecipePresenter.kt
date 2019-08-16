package ar.com.wolox.android.cookbook.coroutines

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class CoroutinesRecipePresenter @Inject constructor(private val loginService: CoroutinesRecipeService) : BasePresenter<CoroutinesRecipeView>() {

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