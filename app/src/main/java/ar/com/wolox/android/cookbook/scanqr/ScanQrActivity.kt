package ar.com.wolox.android.cookbook.scanqr

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.scanqr.scanerror.ScanErrorFragment
import ar.com.wolox.android.cookbook.scanqr.scanmenu.ScanMenuFragment
import ar.com.wolox.android.cookbook.scanqr.scansuccess.ScanSuccessFragment
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import com.google.zxing.integration.android.IntentIntegrator
import javax.inject.Inject

class ScanQrActivity : WolmoActivity(), ScanQrView {

    @Inject
    lateinit var presenter: ScanQrPresenter
    var result: String? = null

    override fun init() {
        presenter = ScanQrPresenter().apply {
            setView(this@ScanQrActivity)
        }
        goToScanMenuFragment()
    }

    override fun layout(): Int = R.layout.activity_scan_qr

    override fun goToSuccessFragment(result: String?) {
        replaceFragment(R.id.vFragment_holder, ScanSuccessFragment.newInstance(result))
    }

    override fun goToErrorFragment() {
        replaceFragment(R.id.vFragment_holder, ScanErrorFragment())
    }

    override fun scanQr() {
        IntentIntegrator(this).run {
            /*
                ADD ANY EXTRA THAT YOU WANT TO PASS TO CAPTURE QR ACTIVITY HERE
             */
            setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) // --> DEFINE THE FORMAT OF THE CODE YOUR SCANNING
            captureActivity = CaptureQrActivity::class.java
            initiateScan()
        }
    }

    override fun goToScanMenuFragment() {
        replaceFragment(R.id.vFragment_holder, ScanMenuFragment())
    }

    override fun onResume() {
        super.onResume()
        if (result != null) {
            presenter.onScannedQR(result!!)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            Activity.RESULT_CANCELED -> {
                /*
                    ADD ANY BEHAVOUR YOU WANT IF YOU BACKPRESS WHEN SCANNING
                 */
            }
            else -> {
                val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
                result?.let {
                    result.contents?.let {
                        this.result = it
                    }
                } ?: run {
                    super.onActivityResult(requestCode, resultCode, data)
                }
            }
        }
    }
}