package ar.com.wolox.android.cookbook.fingerprint.login

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class FingerprintLoginRecipePresenter @Inject constructor() : BasePresenter<FingerprintLoginRecipeView>() {

    fun onLoginButtonClicked(username: String, password: String) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            view?.goToSuccessScreen()
        } else {
            view?.showEmptyFieldsError()
        }
    }
    fun onActivateFingerprintButtonClicked() {
        view?.goToFingerprintActivationScreen()
    }
}
