package ar.com.wolox.android.cookbook.scanqr

import android.app.Activity
import android.content.Intent
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.google.zxing.integration.android.IntentIntegrator
import javax.inject.Inject

class ScanQrPresenter @Inject constructor() : BasePresenter<ScanQrView>() {

    private var scanResult: String? = null

    fun init() = view?.showScanMenuScreen()

    fun onResult(result: String) {
        scanResult = result
        view?.showSuccessScreen(result)
    }

    fun onResume() {
        scanResult?.let { onResult(it) }
    }

    fun onCancelledScan() = view?.showCancelledScanEvent()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            Activity.RESULT_CANCELED -> view?.showCancelledScanEvent()
            else -> {
                IntentIntegrator.parseActivityResult(requestCode, resultCode, data)?.let {
                    it.contents?.let { resultContent ->
                        scanResult = resultContent
                    }
                }
            }
        }
    }
}