package ar.com.wolox.android.cookbook.scanqr

import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import kotlinx.android.synthetic.main.activity_base.view.*

class ScanQrActivity : WolmoActivity(), ScanQrView {

    override fun init() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun layout(): Int = R.layout.activity_base

    override fun goToSuccessFragment() {
        // replaceFragment(R.id.vActivityBaseContent, ScanSuccessFragment() as Fragment)
    }

    override fun goToErrorFragment() {
        replaceFragment(R.id.vActivityBaseContent, ScanErrorFragment())
    }

    override fun scanQr() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun goToScanMenuFragment() {
        replaceFragment(R.id.vActivityBaseContent, ScanMenuFragment())
    }
}