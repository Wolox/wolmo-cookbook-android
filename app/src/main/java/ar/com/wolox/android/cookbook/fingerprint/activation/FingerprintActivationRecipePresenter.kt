package ar.com.wolox.android.cookbook.fingerprint.activation

import androidx.biometric.BiometricManager
import ar.com.wolox.android.cookbook.fingerprint.interfaces.BiometryInfo
import ar.com.wolox.android.cookbook.fingerprint.listener.FingerprintLoginListener
import ar.com.wolox.android.cookbook.fingerprint.model.LoginRequest
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class FingerprintActivationRecipePresenter @Inject constructor() : BasePresenter<FingerprintActivationRecipeView>(), FingerprintLoginListener {

    lateinit var loginRequest: LoginRequest
    private var biometricManager: BiometricManager? = null

    fun onInitFinished(biometricManager: BiometricManager) {
        this.biometricManager = biometricManager
    }

    fun onLoginButtonClicked(username: String, password: String) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            loginRequest = LoginRequest(username, password)
            biometricManager?.let {
                if (it.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK) == BiometricManager.BIOMETRIC_SUCCESS) {
                    view?.showActivateFingerprintDialog()
                }
            }
        } else {
            view?.showEmptyFieldsError()
        }
    }

    override fun onFingerprintLoginSuccess(biometryInfo: BiometryInfo) {
        view?.goToSuccessScreen()
    }

    override fun onFingerprintLoginCancellation() {
        //
    }

    override fun onFingerprintLoginFailure() {
        //
    }
}
