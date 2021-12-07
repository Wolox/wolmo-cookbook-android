package ar.com.wolox.android.cookbook.fingerprint.login

import android.content.Context
import android.util.Base64
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.common.utils.togglePresence
import ar.com.wolox.android.cookbook.databinding.FragmentFingerprintLoginBinding
import ar.com.wolox.android.cookbook.fingerprint.activation.FingerprintActivationRecipeActivity
import ar.com.wolox.android.cookbook.fingerprint.activation.FingerprintActivationRecipeFragment
import ar.com.wolox.android.cookbook.fingerprint.activation.FingerprintActivationRecipeFragment.Companion.KEY_BIOMETRIC_CIPHER_TEXT
import ar.com.wolox.android.cookbook.fingerprint.activation.FingerprintActivationRecipeFragment.Companion.KEY_BIOMETRIC_INITIALIZATION_VECTOR
import ar.com.wolox.android.cookbook.fingerprint.activation.FingerprintActivationRecipeFragment.Companion.KEY_USERNAME
import ar.com.wolox.android.cookbook.fingerprint.interfaces.BiometricDecryptInfo
import ar.com.wolox.android.cookbook.fingerprint.success.FingerprintLoginSuccessActivity
import ar.com.wolox.android.cookbook.fingerprint.utils.BiometricPromptUtils
import ar.com.wolox.android.cookbook.fingerprint.utils.BiometricPromptUtils.authenticateToDecrypt
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import ar.com.wolox.wolmo.core.util.jumpTo
import com.facebook.AccessTokenManager.SHARED_PREFERENCES_NAME
import javax.inject.Inject

class FingerprintLoginRecipeFragment :
    WolmoFragment<FragmentFingerprintLoginBinding, FingerprintLoginRecipePresenter>(),
    FingerprintLoginRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun init() {
        presenter.onInitFinished(
            requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getBoolean(
                    FingerprintActivationRecipeFragment.KEY_BIOMETRIC_ACTIVE, false
                )
        )
    }

    override fun layout() = R.layout.fragment_fingerprint_login

    override fun setListeners() {
        with(binding!!) {
            vFingerprintLoginButton.setOnClickListener {
                presenter.onLoginButtonClicked(
                    vFingerprintUsername.text.toString(),
                    vFingerprintPassword.text.toString()
                )
            }
            vFingerprintActivateButton.setOnClickListener { presenter.onActivateFingerprintButtonClicked() }
        }
    }

    override fun showEmptyFieldsError() {
        toastFactory.show(getString(R.string.fingerprint_login_empty_fields))
    }

    override fun toggleFingerprintLogin(isActive: Boolean) {
        binding!!.vFingerprintActivateButton.togglePresence(!isActive)
    }

    override fun goToFingerprintActivationScreen() {
        requireContext().jumpTo(FingerprintActivationRecipeActivity::class.java)
    }

    override fun goToSuccessScreen() {
        requireContext().jumpTo(FingerprintLoginSuccessActivity::class.java)
    }

    override fun showFingerprintLoginDialog() {
        var pass = String()
        val biometricInfo = object : BiometricDecryptInfo {
            override fun setDecryptedText(text: String) {
                pass = text
                presenter.loginRequest?.password = pass
            }

            override fun getInitializationVector(): ByteArray {
                return Base64.decode(
                    requireContext().getSharedPreferences(
                        SHARED_PREFERENCES_NAME,
                        Context.MODE_PRIVATE
                    ).getString(
                        KEY_BIOMETRIC_INITIALIZATION_VECTOR, String()
                    )!!.toByteArray(),
                    Base64.DEFAULT
                )
            }

            override fun getCipherText(): ByteArray {
                return Base64.decode(
                    requireContext().getSharedPreferences(
                        SHARED_PREFERENCES_NAME,
                        Context.MODE_PRIVATE
                    ).getString(
                        KEY_BIOMETRIC_CIPHER_TEXT, String()
                    )!!.toByteArray(),
                    Base64.DEFAULT
                )
            }

            override fun getUserName(): String {
                val user = requireContext().getSharedPreferences(
                    SHARED_PREFERENCES_NAME,
                    Context.MODE_PRIVATE
                ).getString(
                    KEY_USERNAME, String()
                )!!
                presenter.loginRequest?.username = user
                return user
            }

            override fun getTextToEncrypt() = pass
        }
        val biometricPrompt =
            BiometricPromptUtils.createBiometricPrompt(this, biometricInfo, presenter)
        val promptInfo =
            BiometricPromptUtils.createPromptInfo(requireActivity() as FingerprintLoginRecipeActivity)
        biometricPrompt.let {
            it.authenticateToDecrypt(
                it,
                promptInfo,
                biometricInfo.getInitializationVector()
            )
        }
    }

    override fun showFingerprintCancellationMessage() {
        toastFactory.show(getString(R.string.fingerprint_login_cancellation))
    }

    override fun showFingerprintLockoutError() {
        toastFactory.show(getString(R.string.fingerprint_login_lockout))
    }

    companion object {
        fun newInstance() = FingerprintLoginRecipeFragment()
    }
}
