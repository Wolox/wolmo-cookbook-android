package ar.com.wolox.android.cookbook.scanqr

import android.app.Activity
import android.content.Intent
import android.util.Log
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.scanqr.captureqr.CaptureQrActivity
import ar.com.wolox.android.cookbook.scanqr.scanerror.ScanErrorFragment
import ar.com.wolox.android.cookbook.scanqr.scanmenu.ScanMenuFragment
import ar.com.wolox.android.cookbook.scanqr.scansuccess.ScanSuccessFragment
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import com.google.zxing.integration.android.IntentIntegrator
import javax.inject.Inject

class ScanQrActivity : WolmoActivity(), ScanQrView {

    @Inject
    lateinit var presenter: ScanQrPresenter

    override fun layout(): Int = R.layout.activity_scan_qr

    override fun init() {
    }

    override fun showSuccessScreen(result: String) = replaceFragment(R.id.vFragmentHolder, ScanSuccessFragment.newInstance(result))

    override fun showErrorScreen() = replaceFragment(R.id.vFragmentHolder, ScanErrorFragment())

    override fun showScannerView() {
        IntentIntegrator(this).run {
            /*
                ADD ANY EXTRA THAT YOU WANT TO PASS TO CAPTURE QR ACTIVITY HERE
             */
            setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) // --> DEFINE THE FORMAT OF THE CODE YOUR SCANNING
            captureActivity = CaptureQrActivity::class.java
            initiateScan()
        }
    }

    override fun showScanMenuScreen() = replaceFragment(R.id.vFragmentHolder, ScanMenuFragment())

    override fun showCancelledScanEvent() {
        Log.v("Activity Result Event", "CANCELLED SCAN")
    }

    override fun defaultActivityOnResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_CANCELED -> presenter.onCancelledScan()
            else -> {
                IntentIntegrator.parseActivityResult(requestCode, resultCode, data)?.let {
                    it.contents?.let { resultContent ->
                        presenter.onResult(resultContent)
                    }
                }
            }
        }
    }
}