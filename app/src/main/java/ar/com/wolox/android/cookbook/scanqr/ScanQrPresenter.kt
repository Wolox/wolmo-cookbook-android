package ar.com.wolox.android.cookbook.scanqr

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class ScanQrPresenter @Inject constructor() : BasePresenter<ScanQrView>() {

    lateinit var view2: ScanQrView

    fun setView(view: ScanQrView) {
        this.view2 = view
    }
    fun onScannedQR(result: String) {
        /*
            PERFORM ANY DATA TRANSFORMATION OR REQUEST IF NECESSARY
         */
        view2.goToSuccessFragment(result)
    }
}