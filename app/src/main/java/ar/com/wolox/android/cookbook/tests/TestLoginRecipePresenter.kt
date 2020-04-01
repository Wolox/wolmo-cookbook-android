package ar.com.wolox.android.cookbook.tests

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class TestLoginRecipePresenter @Inject constructor(private val loginService: TestLoginRecipeService) : BasePresenter<TestLoginRecipeView>() {

    fun onLoginButtonClick(email: String, password: String) {
        when {
            email.isEmpty() -> view?.showEmptyEmailError()
            !isValidEmail(email) -> view?.showInvalidEmailError()
            password.isEmpty() -> view?.showEmptyPasswordError()
            else -> loginService.login(email, password, { view?.goToNextWindow() }, { view?.showLoginError() })
        }
    }

    private fun isValidEmail(email: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}