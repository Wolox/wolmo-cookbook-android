package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.analytics.core.AnalyticsManager
import ar.com.wolox.android.cookbook.common.di.CoroutineDispatchersModule
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class ShowMyAgePresenter @Inject constructor(
    private val userRepository: UserRepository,
    private val analyticsManager: AnalyticsManager,
    @Named(CoroutineDispatchersModule.MAIN)
    private val mainDispatcher: CoroutineDispatcher
) : CoroutineBasePresenter<ShowMyAgeView>(mainDispatcher) {

    fun onVisible() = view?.setCurrentScreen(analyticsManager)

    private fun validate(email: String, password: String): Boolean {
        var isValid = true

        if (email.isBlank()) {
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

        launch(mainDispatcher) {
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