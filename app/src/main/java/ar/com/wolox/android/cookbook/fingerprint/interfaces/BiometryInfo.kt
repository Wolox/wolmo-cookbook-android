package ar.com.wolox.android.cookbook.fingerprint.interfaces

interface BiometryInfo {
    fun getUserName(): String
    fun getTextToEncrypt(): String
}

interface BiometricEncryptInfo : BiometryInfo {
    fun setInitializationVector(initializationVector: ByteArray)
    fun setCipherText(cipherText: ByteArray)
}

interface BiometricDecryptInfo : BiometryInfo {
    fun setDecryptedText(text: String)
    fun getInitializationVector(): ByteArray
    fun getCipherText(): ByteArray
}
