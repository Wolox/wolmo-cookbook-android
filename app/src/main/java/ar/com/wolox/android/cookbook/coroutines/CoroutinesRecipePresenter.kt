package ar.com.wolox.android.cookbook.coroutines

import ar.com.wolox.android.cookbook.coroutines.core.CoroutineBasePresenter
import ar.com.wolox.android.cookbook.coroutines.networking.CoroutinesUsersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutinesRecipePresenter @Inject constructor(
    private val usersRepository: CoroutinesUsersRepository
) : CoroutineBasePresenter<CoroutinesRecipeView>() {

    fun onLoginButtonClick(email: String, password: String) {
        when {
            email.isEmpty() -> view.showEmptyEmailError()
            !isValidEmail(email) -> view.showInvalidEmailError()
            password.isEmpty() -> view.showEmptyPasswordError()
            else -> launch {
                usersRepository.getUser(email, password)?.let {
                    view.showWelcomeMessage(it.name)
                } ?: run {
                    view.showLoginError()
                }
            }
        }
    }

    private fun isValidEmail(email: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}