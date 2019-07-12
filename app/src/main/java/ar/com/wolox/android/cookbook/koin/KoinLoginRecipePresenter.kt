package ar.com.wolox.android.cookbook.koin

import android.app.Activity
import android.content.ComponentCallbacks
import androidx.fragment.app.Fragment
import ar.com.wolox.android.cookbook.koin.core.BasePresenter

class KoinLoginRecipePresenter(
    override val view: KoinLoginRecipeView,
    private val loginService: KoinLoginRecipeService
) : BasePresenter<KoinLoginRecipeView> {

    /** You can not do this here, `by inject()` is only for [ComponentCallbacks] like [Activity]s or [Fragment]s. */
    // private val loginService: KoinLoginRecipeService by inject()

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