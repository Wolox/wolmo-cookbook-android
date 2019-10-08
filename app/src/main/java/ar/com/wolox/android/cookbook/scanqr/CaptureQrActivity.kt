package ar.com.wolox.android.cookbook.scanqr

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.KeyEvent
import ar.com.wolox.android.cookbook.R
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.ViewfinderView
import kotlinx.android.synthetic.main.custom_qr_scanner.*
import kotlinx.android.synthetic.main.fragment_capture_qr.*

class CaptureQrActivity : Activity(), DecoratedBarcodeView.TorchListener {

    private var capture: CaptureManager? = null
    private var barcodeScannerView: DecoratedBarcodeView? = null
    private var viewfinderView: ViewfinderView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_capture_qr)
        setUpScannerView(savedInstanceState)
        setOnClickListeners()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture?.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return barcodeScannerView!!.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }

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

    private fun setOnClickListeners() {
        btnGoBack.setOnClickListener {
            startActivity(Intent(this, ScanQrActivity::class.java))
        }
    }

    private fun hasFlash(): Boolean {
        // CHECK IF FLASH IS AVAILABLE
        return applicationContext.packageManager
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
    }

    fun switchFlashlight(switch: Boolean) {
        // TURN ON OR OFF FLASH
        when {
            switch -> barcodeScannerView?.setTorchOn()
            else -> barcodeScannerView?.setTorchOff()
        }
    }

    override fun onTorchOn() {
        // Do any ui modification once the flash is ON
    }

    override fun onTorchOff() {
        // Do any ui modification once the flash is OFF
    }
}