package ar.com.wolox.android.cookbook.tests

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_test_login.vTestLoginEmailInput
import kotlinx.android.synthetic.main.fragment_test_login.vTestLoginLoginBtn
import kotlinx.android.synthetic.main.fragment_test_login.vTestLoginPasswordInput
import javax.inject.Inject

/**
 * Before begin its necessary to configure a Google API Console project and set up your Android Studio project
 * The documentation to do this: https://developers.google.com/identity/sign-in/android/start-integrating
 *
 * Check to add to the build.gradle this:
 *  dependencies {
 *      implementation "com.google.android.gms:play-services-auth:$google_play_services_version"
 *  }
 *
 *  Advice to generate SHA1:
 *  Open Gradle projects with the gradle button on the right of the screen: Tasks -> android -> signingReport
 */

class TestLoginRecipeFragment : WolmoFragment<TestLoginRecipePresenter>(), TestLoginRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_test_login

    override fun init() {
        vTestLoginLoginBtn.setOnClickListener {
            val isValidEmail = isValidEmailInput()
            val isValidPassword = isValidPasswordInput()

            if (isValidEmail && isValidPassword) {
                presenter.onLoginButtonClick(vTestLoginEmailInput.text.toString(), vTestLoginPasswordInput.text.toString())
            }
        }
    }

    private fun isValidEmailInput(): Boolean {
        if (vTestLoginEmailInput.text.isEmpty()) {
            vTestLoginEmailInput.error = getString(R.string.test_login_empty_field)
            return false
        } else if (!isValidEmail(vTestLoginEmailInput.text.toString())) {
            vTestLoginEmailInput.error = getString(R.string.test_login_invalid_email)
            return false
        }

        return true
    }

    private fun isValidEmail(email: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPasswordInput(): Boolean {
        if (vTestLoginPasswordInput.text.isEmpty()) {
            vTestLoginPasswordInput.error = getString(R.string.test_login_empty_field)
            return false
        }

        return true
    }

    override fun goToNextWindow() {
    }

    override fun showLoginError() {
    }
}