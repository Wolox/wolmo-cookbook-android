package ar.com.wolox.android.cookbook.fingerprint.activation

import android.content.Context
import android.util.Base64
import androidx.biometric.BiometricManager
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentFingerprintActivationBinding
import ar.com.wolox.android.cookbook.fingerprint.interfaces.BiometricEncryptInfo
import ar.com.wolox.android.cookbook.fingerprint.success.FingerprintLoginSuccessActivity
import ar.com.wolox.android.cookbook.fingerprint.utils.BiometricPromptUtils
import ar.com.wolox.android.cookbook.fingerprint.utils.BiometricPromptUtils.authenticateToEncrypt
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import ar.com.wolox.wolmo.core.util.jumpTo
import com.facebook.AccessTokenManager.SHARED_PREFERENCES_NAME
import javax.inject.Inject

class FingerprintActivationRecipeFragment :
    WolmoFragment<FragmentFingerprintActivationBinding, FingerprintActivationRecipePresenter>(),
    FingerprintActivationRecipeView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun init() {
        presenter.onInitFinished(
            BiometricManager.from(requireContext())
        )
    }

    override fun layout() = R.layout.fragment_fingerprint_activation

    override fun setListeners() {
        with(binding!!) {
            vFingerprintActivationLoginButton.setOnClickListener {
                presenter.onLoginButtonClicked(
                    vFingerprintActivationUsername.text.toString(),
                    vFingerprintActivationPassword.text.toString()
                )
            }
        }
    }

    override fun showEmptyFieldsError() {
        toastFactory.show(getString(R.string.fingerprint_login_empty_fields))
    }

    override fun showActivateFingerprintDialog() {
        val biometricInfo = object : BiometricEncryptInfo {
            override fun getUserName(): String {
                val username = presenter.loginRequest.username
                requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .edit().putString(
                    KEY_USERNAME,
                    username
                ).apply()
                return username
            }

            override fun getTextToEncrypt(): String {
                return presenter.loginRequest.password
            }

            override fun setInitializationVector(initializationVector: ByteArray) {
                requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .edit().putString(
                    KEY_BIOMETRIC_INITIALIZATION_VECTOR,
                    Base64.encodeToString(initializationVector, Base64.DEFAULT)
                ).apply()
            }

            override fun setCipherText(cipherText: ByteArray) {
                requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .edit().putString(
                    KEY_BIOMETRIC_CIPHER_TEXT,
                    Base64.encodeToString(cipherText, Base64.DEFAULT)
                ).apply()
            }
        }
        val biometricPrompt =
            BiometricPromptUtils.createBiometricPrompt(this, biometricInfo, presenter)
        val promptInfo =
            BiometricPromptUtils.createPromptInfo(requireActivity() as FingerprintActivationRecipeActivity)
        biometricPrompt.let { it.authenticateToEncrypt(it, promptInfo) }
    }

    override fun goToSuccessScreen() {
        requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
            .putBoolean(
                KEY_BIOMETRIC_ACTIVE,
                true
            ).apply()
        requireContext().jumpTo(FingerprintLoginSuccessActivity::class.java)
    }

    override fun showFingerprintCancellationMessage() {
        toastFactory.show(getString(R.string.fingerprint_login_cancellation))
    }

    override fun showFingerprintLockoutError() {
        toastFactory.show(getString(R.string.fingerprint_login_lockout))
    }

    override fun showNoBiometricActiveToast() {
        toastFactory.show(getString(R.string.no_biometrics_active))
    }

    companion object {
        const val KEY_USERNAME = "USERNAME"
        const val KEY_BIOMETRIC_ACTIVE = "BIOMETRIC_ACTIVE"
        const val KEY_BIOMETRIC_INITIALIZATION_VECTOR = "BIOMETRIC_INITIALIZATION_VECTOR"
        const val KEY_BIOMETRIC_CIPHER_TEXT = "BIOMETRIC_CIPHER_TEXT"
        fun newInstance() = FingerprintActivationRecipeFragment()
    }
}
