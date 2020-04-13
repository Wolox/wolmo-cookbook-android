package ar.com.wolox.android.cookbook.scanqr.scansuccess

interface ScanSuccessView {

    fun goToScanMenu()

    fun setUi(result: String?)
}