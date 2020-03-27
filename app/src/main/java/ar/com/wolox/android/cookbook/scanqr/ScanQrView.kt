package ar.com.wolox.android.cookbook.scanqr

import android.content.Intent

interface ScanQrView {

    fun showSuccessFragment(result: String?)

    fun showErrorFragment()

    fun showScannerView()

    fun showScanMenuFragment()

    fun showCancelledScanEvent()

    fun defaultActivityOnResult(requestCode: Int, resultCode: Int, data: Intent?)
}