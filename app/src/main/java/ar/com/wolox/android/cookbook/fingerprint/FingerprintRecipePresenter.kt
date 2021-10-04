package ar.com.wolox.android.cookbook.fingerprint

import androidx.biometric.BiometricManager
import ar.com.wolox.android.cookbook.fingerprint.model.LoginRequest
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class FingerprintRecipePresenter @Inject constructor() : BasePresenter<FingerprintRecipeView>() {

    lateinit var loginRequest: LoginRequest
    private var biometricManager: BiometricManager? = null

    fun onInitFinished(biometricManager: BiometricManager) {
        this.biometricManager = biometricManager
    }

    fun onLoginButtonClicked(username: String, password: String) {
        loginRequest = LoginRequest(username, password)
        biometricManager?.let {
            if (it.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK) == BiometricManager.BIOMETRIC_SUCCESS) {
                view?.showActivateFingerprintDialog()
            }
        }
    }
}
