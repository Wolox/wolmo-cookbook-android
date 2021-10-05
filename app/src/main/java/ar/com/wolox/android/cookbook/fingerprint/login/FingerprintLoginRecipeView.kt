package ar.com.wolox.android.cookbook.fingerprint.login

interface FingerprintLoginRecipeView {
    fun showEmptyFieldsError()
    fun goToFingerprintActivationScreen()
    fun goToSuccessScreen()
}