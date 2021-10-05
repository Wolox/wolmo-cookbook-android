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
    private var secretKeyName: String = "secretKeyName123"
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
                super.onAuthenticationError(errCode, errString)
                Log.d(TAG, "errCode is $errCode and errString is: $errString")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Log.d(TAG, "User biometric rejected")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Log.d(TAG, "Authentication was successful")
                processSuccess(result, biometricInfo, listener)
            }
        }
        return BiometricPrompt(fragment, executor, callback)
    }

    fun createPromptInfo(activity: AppCompatActivity): BiometricPrompt.PromptInfo =
        BiometricPrompt.PromptInfo.Builder().apply {
            setTitle(activity.getString(R.string.fingerprint_prompt_title))
            setSubtitle(activity.getString(R.string.fingerprint_prompt_subtitle))
            setDescription(activity.getString(R.string.fingerprint_prompt_description))
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

    private fun processSuccess(
        result: BiometricPrompt.AuthenticationResult,
        biometricInfo: BiometryInfo,
        listener: FingerprintLoginListener
    ) {
        var pass: String
        listener.onFingerprintLoginSuccess(biometricInfo)
        result.cryptoObject?.let {
            if (readyToEncrypt) {
                val encryptInfo = biometricInfo as BiometricEncryptInfo
                val text = biometricInfo.getTextToEncrypt()
                val encryptedData = CryptographyManager().encryptData(text, it.cipher!!)
                val cipherText = encryptedData.ciphertext
                val initializationVector = encryptedData.initializationVector
                encryptInfo.setCipherText(cipherText)
                encryptInfo.setInitializationVector(initializationVector)
                pass = String(cipherText, Charsets.UTF_8)
            } else {
                val decryptInfo = biometricInfo as BiometricDecryptInfo
                pass = CryptographyManager().decryptData(decryptInfo.getCipherText(), it.cipher!!)
                decryptInfo.setDecryptedText(pass)
            }
        }
    }
}
