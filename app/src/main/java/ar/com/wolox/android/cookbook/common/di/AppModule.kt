package ar.com.wolox.android.cookbook.common.di

import ar.com.wolox.android.cookbook.WolmoTestActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeFragment
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeActivity
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeFragment
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerActivity
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerFragment
import ar.com.wolox.android.cookbook.scanqr.CaptureQrActivity
import ar.com.wolox.android.cookbook.scanqr.ScanQrActivity
import ar.com.wolox.android.cookbook.scanqr.scanerror.ScanErrorFragment
import ar.com.wolox.android.cookbook.scanqr.scanmenu.ScanMenuFragment
import ar.com.wolox.android.cookbook.scanqr.scansuccess.ScanSuccessFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    abstract fun testActivity(): WolmoTestActivity

    @ContributesAndroidInjector
    abstract fun recipePickerActivity(): RecipePickerActivity

    @ContributesAndroidInjector
    abstract fun recipePickerFragment(): RecipePickerFragment

    @ContributesAndroidInjector
    abstract fun googleLoginActivity(): GoogleLoginRecipeActivity

    @ContributesAndroidInjector
    abstract fun googleLoginFragment(): GoogleLoginRecipeFragment

    @ContributesAndroidInjector
    abstract fun facebookLoginActivity(): FacebookLoginRecipeActivity

    @ContributesAndroidInjector
    abstract fun facebookLoginFragment(): FacebookLoginRecipeFragment

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
