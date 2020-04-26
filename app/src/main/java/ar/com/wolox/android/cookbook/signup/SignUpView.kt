package ar.com.wolox.android.cookbook.signup

interface SignUpView {

    fun validateForm(): Boolean

    fun toggleSignUpResult(result: Boolean)
}