package ar.com.wolox.android.cookbook.fingerprint.success

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class FingerprintLoginSuccessPresenter @Inject constructor() :
    BasePresenter<FingerprintLoginSuccessView>() {

    fun onBackButtonPressed() {
        view?.goBack()
    }
}
