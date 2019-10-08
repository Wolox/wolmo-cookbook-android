package ar.com.wolox.android.cookbook.scanqr

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_scan_error.*

class ScanErrorFragment : WolmoFragment<ScanQrPresenter>() {
    override fun init() {
        setOnClickListeners()
    }

    override fun layout(): Int = R.layout.fragment_scan_error

    private fun setOnClickListeners() {
        bGoToScanMenu.setOnClickListener {
            (activity as ScanQrActivity).goToScanMenuFragment()
        }

        bTryAgainScan.setOnClickListener {
            (activity as ScanQrActivity).scanQr()
        }
    }
}