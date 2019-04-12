package ar.com.wolox.android.cookbook.tests

import android.content.Intent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.datasync.DataSyncRecipeActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_test_login.vTestLoginEmailInput
import kotlinx.android.synthetic.main.fragment_test_login.vTestLoginLoginBtn
import kotlinx.android.synthetic.main.fragment_test_login.vTestLoginPasswordInput
import javax.inject.Inject

class TestLoginRecipeFragment : WolmoFragment<TestLoginRecipePresenter>(), TestLoginRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_test_login

    override fun init() {
        vTestLoginLoginBtn.setOnClickListener {
            val isValidEmail = verifyEmailInput()
            val isValidPassword = verifyPasswordInput()

            if (isValidEmail && isValidPassword) {
                presenter.onLoginButtonClick(vTestLoginEmailInput.text.toString(), vTestLoginPasswordInput.text.toString())
            }
        }
    }

    private fun verifyEmailInput(): Boolean {
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

    private fun verifyPasswordInput(): Boolean {
        if (vTestLoginPasswordInput.text.isEmpty()) {
            vTestLoginPasswordInput.error = getString(R.string.test_login_empty_field)
            return false
        }

        return true
    }

    // This will go to DataSyncRecipe just because
    override fun goToNextWindow() =
            requireActivity().startActivity(Intent(requireContext(), DataSyncRecipeActivity::class.java))

    override fun showLoginError() { vTestLoginEmailInput.error = "Hola" }
}