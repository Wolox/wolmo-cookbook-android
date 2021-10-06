package ar.com.wolox.android.cookbook.fingerprint.interfaces

interface BiometricDecryptInfo : BiometryInfo {
    fun setDecryptedText(text: String)
    fun getInitializationVector(): ByteArray
    fun getCipherText(): ByteArray
}
