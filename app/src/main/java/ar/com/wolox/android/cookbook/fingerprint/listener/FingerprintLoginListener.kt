package ar.com.wolox.android.cookbook.fingerprint.listener

import ar.com.wolox.android.cookbook.fingerprint.interfaces.BiometryInfo

interface FingerprintLoginListener {
    fun onFingerprintLoginSuccess(biometryInfo: BiometryInfo)
    fun onFingerprintLoginCancellation()
    fun onFingerprintLoginFailure()
}
