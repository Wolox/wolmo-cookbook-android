package ar.com.wolox.android.cookbook.tests

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class TestLoginRecipePresenter @Inject constructor(private val loginService: TestLoginRecipeService) : BasePresenter<TestLoginRecipeView>() {

    fun onLoginButtonClick(email: String, password: String) {
        loginService.login(email, password, { view.goToNextWindow() }, { view.showLoginError() })
    }
}