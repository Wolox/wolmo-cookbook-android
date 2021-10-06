package ar.com.wolox.android.cookbook.fingerprint.login

import ar.com.wolox.android.cookbook.fingerprint.interfaces.BiometryInfo
import ar.com.wolox.android.cookbook.fingerprint.listener.FingerprintLoginListener
import ar.com.wolox.android.cookbook.fingerprint.model.LoginRequest
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class FingerprintLoginRecipePresenter @Inject constructor() : BasePresenter<FingerprintLoginRecipeView>(),
    FingerprintLoginListener {

    var loginRequest: LoginRequest? = null

    fun onInitFinished(isBiometricActive: Boolean) {
        if (isBiometricActive) {
            view?.showFingerprintLoginDialog()
        }
        view?.toggleFingerprintLogin(isBiometricActive)
    }

    fun onLoginButtonClicked(username: String, password: String) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            loginRequest = LoginRequest(username, password)
            view?.goToSuccessScreen()
        } else {
            view?.showEmptyFieldsError()
        }
    }
    fun onActivateFingerprintButtonClicked() {
        view?.goToFingerprintActivationScreen()
    }

    override fun onFingerprintLoginSuccess(biometryInfo: BiometryInfo) {
        onLoginButtonClicked(biometryInfo.getUserName(), biometryInfo.getTextToEncrypt())
    }

    override fun onFingerprintLoginCancellation() {
        view?.showFingerprintCancellationMessage()
    }

    override fun onFingerprintLoginFailure() {
        view?.showFingerprintLockoutError()
    }
}
