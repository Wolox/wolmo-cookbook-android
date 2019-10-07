package ar.com.wolox.android.cookbook.scanqr

interface ScanQrView {
    fun goToSuccessFragment()

    fun goToErrorFragment()

    fun scanQr()

    fun goToScanMenuFragment()
}