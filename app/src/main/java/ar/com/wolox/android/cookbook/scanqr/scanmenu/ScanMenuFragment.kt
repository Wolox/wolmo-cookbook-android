package ar.com.wolox.android.cookbook.scanqr.scanmenu

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.scanqr.ScanQrActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_scan_menu.*
import javax.inject.Inject

class ScanMenuFragment @Inject constructor() : WolmoFragment<ScanMenuPresenter>(), ScanMenuView {

    override fun init() {
        setOnClickListeners()
    }

    override fun layout(): Int = R.layout.fragment_scan_menu

    private fun setOnClickListeners() {
        bScanQr.setOnClickListener {
            presenter.onScanClicked()
        }

        bGenerateQr.setOnClickListener {
            // TODO GENERATE QR
        }
    }

    override fun goToScanScreen() {
        (activity as ScanQrActivity).scanQr()
    }
}