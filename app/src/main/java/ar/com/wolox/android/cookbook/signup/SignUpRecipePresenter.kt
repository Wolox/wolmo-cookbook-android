package ar.com.wolox.android.cookbook.signup

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class SignUpRecipePresenter @Inject constructor() : BasePresenter<SignUpView>() {

    fun onSignUpButtonClicked() {
        with(view!!) {
            toggleSignUpResult(validateForm())
        }
    }
}