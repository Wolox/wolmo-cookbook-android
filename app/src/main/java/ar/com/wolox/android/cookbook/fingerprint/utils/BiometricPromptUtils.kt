package ar.com.wolox.android.cookbook.fingerprint.utils

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.fingerprint.interfaces.BiometricDecryptInfo
import ar.com.wolox.android.cookbook.fingerprint.interfaces.BiometricEncryptInfo
import ar.com.wolox.android.cookbook.fingerprint.interfaces.BiometryInfo
import ar.com.wolox.android.cookbook.fingerprint.listener.FingerprintLoginListener

object BiometricPromptUtils {
    private const val TAG: String = "BiometricPromptUtils"
    private var secretKeyName: String = "key"
    private var readyToEncrypt: Boolean = false

    fun createBiometricPrompt(
        fragment: Fragment,
        biometricInfo: BiometryInfo,
        listener: FingerprintLoginListener
    ): BiometricPrompt {
        val context = fragment.requireContext()
        val executor = ContextCompat.getMainExecutor(context)

        val callback = object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationError(errCode: Int, errString: CharSequence) {
                if (errCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    listener.onFingerprintLoginCancellation()
                }
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onBiometricLoginSuccess(result, biometricInfo, listener)
            }
        }
        return BiometricPrompt(fragment, executor, callback)
    }

    fun createPromptInfo(activity: AppCompatActivity): BiometricPrompt.PromptInfo =
        BiometricPrompt.PromptInfo.Builder().apply {
            setTitle(activity.getString(R.string.fingerprint_prompt_title))
            setSubtitle(activity.getString(R.string.fingerprint_prompt_subtitle))
            setConfirmationRequired(false)
            setNegativeButtonText(activity.getString(R.string.fingerprint_prompt_dismiss))
        }.build()

    fun BiometricPrompt.authenticateToEncrypt(
        biometricPrompt: BiometricPrompt,
        promptInfo: BiometricPrompt.PromptInfo
    ) {
        readyToEncrypt = true
        val cipher = CryptographyManager().getInitializedCipherForEncryption(secretKeyName)
        biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
    }

    fun BiometricPrompt.authenticateToDecrypt(
        biometricPrompt: BiometricPrompt,
        promptInfo: BiometricPrompt.PromptInfo,
        vector: ByteArray
    ) {
        readyToEncrypt = false
        val cipher =
            CryptographyManager().getInitializedCipherForDecryption(secretKeyName, vector)
        biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
    }

    private fun onBiometricLoginSuccess(
        result: BiometricPrompt.AuthenticationResult,
        biometricInfo: BiometryInfo,
        listener: FingerprintLoginListener
    ) {
        var pass: String
        result.cryptoObject?.let {
            val biometryInfo: BiometryInfo
            if (readyToEncrypt) {
                biometryInfo = biometricInfo as BiometricEncryptInfo
                val text = biometricInfo.getTextToEncrypt()
                val encryptedData = CryptographyManager().encryptData(text, it.cipher!!)
                val cipherText = encryptedData.ciphertext
                val initializationVector = encryptedData.initializationVector
                biometryInfo.setCipherText(cipherText)
                biometryInfo.setInitializationVector(initializationVector)
                pass = String(cipherText, Charsets.UTF_8)
            } else {
                biometryInfo = biometricInfo as BiometricDecryptInfo
                pass = CryptographyManager().decryptData(biometryInfo.getCipherText(), it.cipher!!)
                biometryInfo.setDecryptedText(pass)
            }
            Log.e("asd", biometryInfo.getUserName())
            Log.e("pasd", biometryInfo.getTextToEncrypt())
            listener.onFingerprintLoginSuccess(biometryInfo)
        }
    }
}
