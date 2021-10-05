package ar.com.wolox.android.cookbook.fingerprint.login

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.fingerprint.activation.FingerprintActivationRecipeActivity
import ar.com.wolox.android.cookbook.fingerprint.success.FingerprintLoginSuccessActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import ar.com.wolox.wolmo.core.util.jumpTo
import kotlinx.android.synthetic.main.fragment_fingerprint_login.*
import kotlinx.android.synthetic.main.fragment_google_login.*
import javax.inject.Inject

class FingerprintLoginRecipeFragment : WolmoFragment<FingerprintLoginRecipePresenter>(), FingerprintLoginRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun init() {
    }

    override fun layout() = R.layout.fragment_fingerprint_login

    override fun setListeners() {
        vFingerprintLoginButton.setOnClickListener {
            presenter.onLoginButtonClicked(
                vFingerprintUsername.text.toString(),
                vFingerprintPassword.text.toString()
            )
        }
        vFingerprintActivateButton.setOnClickListener { presenter.onActivateFingerprintButtonClicked() }
    }

    override fun showEmptyFieldsError() {
        toastFactory.show("Empty fields")
    }

    override fun goToFingerprintActivationScreen() {
        requireContext().jumpTo(FingerprintActivationRecipeActivity::class.java)
    }

    override fun goToSuccessScreen() {
        requireContext().jumpTo(FingerprintLoginSuccessActivity::class.java)
    }

    companion object {
        fun newInstance() = FingerprintLoginRecipeFragment()
    }
}
