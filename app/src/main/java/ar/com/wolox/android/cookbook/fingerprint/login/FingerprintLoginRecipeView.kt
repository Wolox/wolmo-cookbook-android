package ar.com.wolox.android.cookbook.fingerprint.login

interface FingerprintLoginRecipeView {
    fun showEmptyFieldsError()
    fun toggleFingerprintLogin(isActive: Boolean)
    fun goToFingerprintActivationScreen()
    fun goToSuccessScreen()
    fun showFingerprintLoginDialog()
    fun showFingerprintCancellationMessage()
    fun showFingerprintLockoutError()
}