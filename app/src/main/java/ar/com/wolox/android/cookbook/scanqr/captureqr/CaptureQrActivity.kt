package ar.com.wolox.android.cookbook.scanqr.captureqr

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.KeyEvent
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.scanqr.ScanQrActivity
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.ViewfinderView
import kotlinx.android.synthetic.main.custom_qr_scanner.*
import kotlinx.android.synthetic.main.fragment_capture_qr.*
import javax.inject.Inject

class CaptureQrActivity : WolmoActivity(), DecoratedBarcodeView.TorchListener, CaptureQrView {
    @Inject
    lateinit var presenter: CaptureQrPresenter

    private var capture: CaptureManager? = null
    private var barcodeScannerView: DecoratedBarcodeView? = null
    private var viewfinderView: ViewfinderView? = null

    override fun layout(): Int = R.layout.fragment_capture_qr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpScannerView(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        capture?.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture?.onDestroy()
    }

    override fun init() {
        setListeners()
        presenter.attachView(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture?.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean = barcodeScannerView!!.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)

    private fun setUpScannerView(savedInstanceState: Bundle?) {
        viewfinderView = zxing_viewfinder_view
        barcodeScannerView = zxing_qr_scanner.apply {
            setTorchListener(this@CaptureQrActivity)
        }
        capture = CaptureManager(this, barcodeScannerView!!).apply {
            initializeFromIntent(this@CaptureQrActivity.intent, savedInstanceState)
            decode()
        }
    }

    override fun setListeners() {
        super.setListeners()
        vGoBackButton.setOnClickListener {
            presenter.onBackButtonPressed()
        }
    }

    private fun hasFlash(): Boolean = applicationContext.packageManager
            .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

    fun switchFlashlight(switch: Boolean) {
/*
        // TURN ON OR OFF FLASH
*/
        when {
            switch -> barcodeScannerView?.setTorchOn()
            else -> barcodeScannerView?.setTorchOff()
        }
    }

    override fun onTorchOn() { /*ON FLASH ON*/
    }

    override fun onTorchOff() { /* ON FLASH OFF*/
    }

    override fun showScanActivity() {
        startActivity(Intent(this, ScanQrActivity::class.java))
    }
}