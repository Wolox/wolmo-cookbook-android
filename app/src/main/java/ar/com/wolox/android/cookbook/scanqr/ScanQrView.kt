package ar.com.wolox.android.cookbook.scanqr

import android.content.Intent

interface ScanQrView {

    fun showSuccessScreen(result: String)

    fun showErrorScreen()

    fun showScannerView()

    fun showScanMenuScreen()

    fun showCancelledScanEvent()

    fun defaultActivityOnResult(requestCode: Int, resultCode: Int, data: Intent?)
}