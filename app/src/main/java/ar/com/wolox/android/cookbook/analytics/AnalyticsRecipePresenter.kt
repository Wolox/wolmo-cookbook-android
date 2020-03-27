package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.analytics.core.AnalyticsManager
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnalyticsRecipePresenter @Inject constructor(
    private val userRepository: UserRepository,
    private val analyticsManager: AnalyticsManager
) : CoroutineBasePresenter<AnalyticsRecipeView>() {

    private fun validate(email: String, password: String): Boolean {
        var isValid = true

            println("entro acá1 -> '$email'")
        if (email.isBlank()) {
            println("entro acá2")
            analyticsManager.logEvent(EmptyEmailEvent)
            view?.showEmailEmptyError()
            isValid = false
        }

        if (password.isBlank()) {
            analyticsManager.logEvent(EmptyPasswordEvent)
            view?.showPasswordEmptyError()
            isValid = false
        }

        return isValid
    }

    fun onAgeRequestButtonClicked(email: String, password: String) {
        if (!validate(email, password)) {
            return
        }

        launch {
            try {
                userRepository.getUser(email, password)?.let {
                    analyticsManager.logEvent(AgeRequestSuccessful(email))
                    view?.showAge(it.name, it.age)
                } ?: run {
                    analyticsManager.logEvent(AgeRequestError(email))
                    view?.showInvalidUserError()
                }
            } catch (error: ServiceUnavailableException) {
                analyticsManager.logEvent(AgeRequestServiceUnavailable(email))
                view?.showServeUnavailableError()
            }
        }
    }

    fun onHelpButtonClicked() {
        analyticsManager.logEvent(OpenHelp)
        view?.openHelp()
    }
}