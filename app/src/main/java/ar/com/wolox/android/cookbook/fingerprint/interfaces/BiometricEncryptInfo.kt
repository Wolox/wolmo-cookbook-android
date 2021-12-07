package ar.com.wolox.android.cookbook.fingerprint.interfaces

interface BiometricEncryptInfo : BiometryInfo {
    fun setInitializationVector(initializationVector: ByteArray)
    fun setCipherText(cipherText: ByteArray)
}
