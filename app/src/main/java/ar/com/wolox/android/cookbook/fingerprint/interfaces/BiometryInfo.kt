package ar.com.wolox.android.cookbook.fingerprint.interfaces

interface BiometryInfo {
    fun getUserName(): String
    fun getTextToEncrypt(): String
    fun setInitializationVector(initializationVector: ByteArray)
    fun getInitializationVector(): ByteArray
    fun setCipherText(cipherText: ByteArray)
    fun getCipherText(): ByteArray
}
