package ar.com.wolox.android.cookbook.fingerprint

import android.content.Context
import android.util.Base64
import androidx.biometric.BiometricManager
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.fingerprint.interfaces.BiometryInfo
import ar.com.wolox.android.cookbook.fingerprint.utils.BiometricPromptUtils
import ar.com.wolox.android.cookbook.fingerprint.utils.BiometricPromptUtils.authenticateToEncrypt
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.facebook.AccessTokenManager.SHARED_PREFERENCES_NAME
import kotlinx.android.synthetic.main.fragment_fingerprint_login.*

class FingerprintRecipeFragment : WolmoFragment<FingerprintRecipePresenter>(), FingerprintRecipeView {

    override fun init() {
        presenter.onInitFinished(
            BiometricManager.from(requireContext())
        )
    }

    override fun layout() = R.layout.fragment_fingerprint_login

    override fun setListeners() {
        vFingerprintLoginButton.setOnClickListener {
            presenter.onLoginButtonClicked(
                vFingerprintUsername.text.toString(),
                vFingerprintPassword.text.toString()
            )
        }
    }

    override fun showActivateFingerprintDialog() {
        val biometricInfo = object : BiometryInfo {

            override fun getUserName(): String {
                return presenter.loginRequest.username
            }

            override fun getTextToEncrypt(): String {
                return presenter.loginRequest.password
            }

            override fun setInitializationVector(initializationVector: ByteArray) {
                requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putString(
                    KEY_BIOMETRIC_INITIALIZATION_VECTOR,
                    Base64.encodeToString(initializationVector, Base64.DEFAULT)
                ).apply()
            }

            override fun getInitializationVector(): ByteArray {
                return ByteArray(0)
            }

            override fun setCipherText(cipherText: ByteArray) {
                requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putString(
                    KEY_BIOMETRIC_CIPHER_TEXT,
                    Base64.encodeToString(cipherText, Base64.DEFAULT)
                ).apply()
            }

            override fun getCipherText(): ByteArray {
                return ByteArray(0)
            }
        }
        val biometricPrompt = BiometricPromptUtils.createBiometricPrompt(this, biometricInfo)
        val promptInfo = BiometricPromptUtils.createPromptInfo(requireActivity() as FingerprintRecipeActivity)
        biometricPrompt.let { it.authenticateToEncrypt(it, promptInfo) }
    }

    companion object {
        private const val KEY_BIOMETRIC_INITIALIZATION_VECTOR = "BIOMETRIC_INITIALIZATION_VECTOR"
        private const val KEY_BIOMETRIC_CIPHER_TEXT = "BIOMETRIC_CIPHER_TEXT"
        fun newInstance() = FingerprintRecipeFragment()
    }
}