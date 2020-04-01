package ar.com.wolox.android.cookbook.scanqr.captureqr

import android.os.Bundle
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class CaptureQrPresenter @Inject constructor() : BasePresenter<CaptureQrView>() {

    fun init (bundle: Bundle) {
        view?.setUpScannerView(bundle)
    }

    fun onBackButtonPressed() {
        view?.showScanActivity()
    }
}