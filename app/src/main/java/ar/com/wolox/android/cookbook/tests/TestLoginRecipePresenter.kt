package ar.com.wolox.android.cookbook.tests

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class TestLoginRecipePresenter @Inject constructor(private val loginService: TestLoginRecipeService) : BasePresenter<TestLoginRecipeView>() {

    fun onLoginButtonClick(email: String, password: String) {
        val isValidEmail = verifyEmail(email)
        val isValidPassword = verifyPassword(password)

        if (isValidEmail && isValidPassword) {
            loginService.login(email, password, { view.goToNextWindow() }, { view.showLoginError() })
        }
    }

    private fun verifyEmail(email: String): Boolean {
        if (email.isEmpty()) {
            view.showEmptyEmailError()
            return false
        } else if (!isValidEmail(email)) {
            view.showInvalidEmailError()
            return false
        }

        return true
    }

    private fun isValidEmail(email: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun verifyPassword(password: String): Boolean {
        if (password.isEmpty()) {
            view.showEmptyPasswordError()
            return false
        }

        return true
    }
}