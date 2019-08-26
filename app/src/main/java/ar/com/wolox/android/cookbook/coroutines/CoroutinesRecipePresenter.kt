package ar.com.wolox.android.cookbook.coroutines

import ar.com.wolox.android.cookbook.coroutines.exceptions.CallFailureException
import ar.com.wolox.android.cookbook.coroutines.exceptions.ResponseFailedException
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class CoroutinesRecipePresenter @Inject constructor(private val usersRepository: CoroutinesUsersRepository) :
        BasePresenter<CoroutinesRecipeView>() {

    fun onLoginButtonClick(email: String, password: String) {
        when {
            email.isEmpty() -> view.showEmptyEmailError()
            !isValidEmail(email) -> view.showInvalidEmailError()
            password.isEmpty() -> view.showEmptyPasswordError()
            else -> {
                try {
                    usersRepository.getUser(email, password) { user ->
                        user?.let { view.showWelcomeMessage(it.name) } ?: view.showLoginError()
                    }
                } catch (e: ResponseFailedException) {
                    view.showLoginError()
                } catch (e: CallFailureException) {
                    view.showLoginError()
                }
            }
        }
    }

    private fun isValidEmail(email: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}