package ar.com.wolox.android.cookbook.scanqr.scanmenu

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class ScanMenuPresenter @Inject constructor() : BasePresenter<ScanMenuView> () {

    fun onScanClicked() {
        view?.goToScanScreen()
    }
}