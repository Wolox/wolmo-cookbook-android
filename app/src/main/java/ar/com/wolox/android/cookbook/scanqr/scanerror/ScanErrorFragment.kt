package ar.com.wolox.android.cookbook.scanqr.scanerror

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.scanqr.ScanQrActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_scan_error.*
import javax.inject.Inject

class ScanErrorFragment @Inject constructor() : WolmoFragment<ScanErrorPresenter>(), ScanErrorView {

    override fun layout(): Int = R.layout.fragment_scan_error

    override fun init() {}

    override fun setListeners() {
        super.setListeners()
        vGoToScanMenuButton.setOnClickListener {
            presenter.onBackToMenuPressed()
        }
    }

    override fun showScanMenuScreen() {
        (activity as ScanQrActivity).showScanMenuScreen()
    }
}