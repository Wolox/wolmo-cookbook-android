package ar.com.wolox.android.cookbook.scanqr

interface ScanQrView {

    fun goToSuccessFragment(result: String?)

    fun goToErrorFragment()

    fun scanQr()

    fun goToScanMenuFragment()
}