package ar.com.wolox.android.cookbook.common.di

import ar.com.wolox.android.cookbook.WolmoTestActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeFragment
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeActivity
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeFragment
import ar.com.wolox.android.cookbook.instagramlogin.InstagramLoginRecipeActivity
import ar.com.wolox.android.cookbook.instagramlogin.InstagramLoginRecipeFragment
import ar.com.wolox.android.cookbook.mpchart.MpChartRecipeActivity
import ar.com.wolox.android.cookbook.mpchart.MpChartRecipeFragment
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerActivity
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerFragment
import ar.com.wolox.android.cookbook.room.RoomRecipeActivity
import ar.com.wolox.android.cookbook.room.RoomRecipeFragment
import ar.com.wolox.android.cookbook.twitterlogin.TwitterLoginRecipeActivity
import ar.com.wolox.android.cookbook.twitterlogin.TwitterLoginRecipeFragment
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
    abstract fun twitterLoginActivity(): TwitterLoginRecipeActivity

    @ContributesAndroidInjector
    abstract fun twitterLoginFragment(): TwitterLoginRecipeFragment

    @ContributesAndroidInjector
    abstract fun instagramLoginActivity(): InstagramLoginRecipeActivity

    @ContributesAndroidInjector
    abstract fun instagramLoginFragment(): InstagramLoginRecipeFragment

    @ContributesAndroidInjector
    abstract fun roomActivity(): RoomRecipeActivity

    @ContributesAndroidInjector
    abstract fun roomFragment(): RoomRecipeFragment

    @ContributesAndroidInjector
    abstract fun mpChartActivity(): MpChartRecipeActivity

    @ContributesAndroidInjector
    abstract fun mpChartFragment(): MpChartRecipeFragment
}
