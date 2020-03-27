package ar.com.wolox.android.cookbook.scanqr

import android.app.Activity
import android.content.Intent
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.google.zxing.integration.android.IntentIntegrator
import javax.inject.Inject

class ScanQrPresenter @Inject constructor() : BasePresenter<ScanQrView>() {

    private var activityResult: String? = null

    fun handleResult(result: String) {
        /*
            PERFORM ANY DATA TRANSFORMATION OR REQUEST IF NECESSARY
         */
        view.showSuccessFragment(result)
    }

    fun onResumeActivity() {
        activityResult?.let { handleResult(it) }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            Activity.RESULT_CANCELED -> view.showCancelledScanEvent()
            else -> {
                IntentIntegrator.parseActivityResult(requestCode, resultCode, data)?.let {
                    it.contents?.let { resultContent ->
                        activityResult = resultContent
                    }
                }
            }
        }
    }
}