package ar.com.wolox.android.cookbook.fingerprint.interfaces

interface BiometryInfo {
    fun getUserName(): String
    fun getTextToEncrypt(): String
}
