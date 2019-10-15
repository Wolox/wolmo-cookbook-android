package ar.com.wolox.android.cookbook.scanqr.scansuccess

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class ScanSuccessPresenter @Inject constructor() : BasePresenter<ScanSuccessView>() {

    fun init(result: String?) {
        view.setUi(result)
    }

    fun onScanMenuClicked() {
        view.goToScanMenu()
    }
}