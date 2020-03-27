package ar.com.wolox.android.cookbook.scanqr

import ar.com.wolox.android.cookbook.scanqr.scanerror.ScanErrorFragment
import ar.com.wolox.android.cookbook.scanqr.scanmenu.ScanMenuFragment
import ar.com.wolox.android.cookbook.scanqr.scansuccess.ScanSuccessFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScanQrModule {
    @ContributesAndroidInjector
    abstract fun scanQrActivity(): ScanQrActivity

    @ContributesAndroidInjector
    abstract fun captureQrActivity(): CaptureQrActivity

    @ContributesAndroidInjector
    abstract fun scanMenuFragment(): ScanMenuFragment

    @ContributesAndroidInjector
    abstract fun scanSuccessFragment(): ScanSuccessFragment

    @ContributesAndroidInjector
    abstract fun scanErrorFragment(): ScanErrorFragment
}