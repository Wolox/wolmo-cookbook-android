package ar.com.wolox.android.cookbook.common.di

import ar.com.wolox.android.cookbook.WolmoTestActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeFragment
import ar.com.wolox.android.cookbook.fingerprint.activation.FingerprintActivationRecipeActivity
import ar.com.wolox.android.cookbook.fingerprint.activation.FingerprintActivationRecipeFragment
import ar.com.wolox.android.cookbook.fingerprint.login.FingerprintLoginRecipeActivity
import ar.com.wolox.android.cookbook.fingerprint.login.FingerprintLoginRecipeFragment
import ar.com.wolox.android.cookbook.fingerprint.success.FingerprintLoginSuccessActivity
import ar.com.wolox.android.cookbook.fingerprint.success.FingerprintLoginSuccessFragment
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeActivity
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeFragment
import ar.com.wolox.android.cookbook.graphQl.OrdersActivity
import ar.com.wolox.android.cookbook.graphQl.OrdersFragment
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerActivity
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerFragment
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
    abstract fun graphQlActivity(): OrdersActivity

    @ContributesAndroidInjector
    abstract fun graphQlFragment(): OrdersFragment

    @ContributesAndroidInjector
    abstract fun fingerprintActivationRecipeActivity(): FingerprintActivationRecipeActivity

    @ContributesAndroidInjector
    abstract fun fingerprintActivationRecipeFragment(): FingerprintActivationRecipeFragment

    @ContributesAndroidInjector
    abstract fun fingerprintLoginRecipeActivity(): FingerprintLoginRecipeActivity

    @ContributesAndroidInjector
    abstract fun fingerprintLoginRecipeFragment(): FingerprintLoginRecipeFragment

    @ContributesAndroidInjector
    abstract fun fingerprintLoginSuccessActivity(): FingerprintLoginSuccessActivity

    @ContributesAndroidInjector
    abstract fun fingerprintLoginSuccessFragment(): FingerprintLoginSuccessFragment
}
