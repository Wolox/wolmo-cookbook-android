package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.analytics.core.AnalyticsManager
import ar.com.wolox.android.cookbook.databinding.FragmentShowMyAgeBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import ar.com.wolox.wolmo.core.util.openBrowser
import javax.inject.Inject

class ShowMyAgeFragment @Inject constructor() :
    WolmoFragment<FragmentShowMyAgeBinding, ShowMyAgePresenter>(), ShowMyAgeView {

    @Inject
    lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_show_my_age

    override fun init() {
    }

    override fun setCurrentScreen(analyticsManager: AnalyticsManager) =
        analyticsManager.setCurrentScreen(this)

    override fun setListeners() {
        with(binding!!) {
            ageRequestButton.setOnClickListener {
                presenter.onAgeRequestButtonClicked(
                    emailInput.text.toString(),
                    passwordInput.text.toString()
                )
            }
            helpButton.setOnClickListener {
                presenter.onHelpButtonClicked()
            }
            crashButton.setOnClickListener {
                presenter.onCrashButtonClicked()
            }
        }
    }

    override fun onVisible() {
        presenter.onVisible()
    }

    override fun openHelp() = requireContext().openBrowser(HELP_URL)

    override fun showAge(name: String, age: Int) =
        toastFactory.show(resources.getString(R.string.analytics_age, name, age))

    override fun showEmailEmptyError() = toastFactory.show(R.string.analytics_empty_email_error)

    override fun showPasswordEmptyError() =
        toastFactory.show(R.string.analytics_empty_password_error)

    override fun showInvalidUserError() = toastFactory.show(R.string.analytics_invalid_user_error)

    override fun showServeUnavailableError() =
        toastFactory.show(R.string.analytics_service_unavailable_error)

    override fun forceCrashApp() {
        throw RuntimeException(TEST_CRASH)
    }

    companion object {
        const val TEST_CRASH = "Test Crash for Crashlytics"
        const val HELP_URL = "https://www.google.com"
    }
}