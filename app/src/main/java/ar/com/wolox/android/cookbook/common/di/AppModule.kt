package ar.com.wolox.android.cookbook.common.di

import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeActivity
import ar.com.wolox.android.cookbook.facebooklogin.FacebookLoginRecipeFragment
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeActivity
import ar.com.wolox.android.cookbook.googlelogin.GoogleLoginRecipeFragment
import ar.com.wolox.android.cookbook.motionLayout.MotionActivity
import ar.com.wolox.android.cookbook.motionLayout.MotionMenuFragment
import ar.com.wolox.android.cookbook.motionLayout.viewPager.ViewPagerFragment
import ar.com.wolox.android.cookbook.motionLayout.viewPager.ViewPagerLottieFragment
import ar.com.wolox.android.cookbook.motionLayout.viewPager.page1.Page1Fragment
import ar.com.wolox.android.cookbook.motionLayout.viewPager.page2.Page2Fragment
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerActivity
import ar.com.wolox.android.cookbook.recipepicker.RecipePickerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

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
    abstract fun motionLayoutActivity(): MotionActivity

    @ContributesAndroidInjector
    abstract fun motionLayoutMenuFragment(): MotionMenuFragment

    @ContributesAndroidInjector
    abstract fun viewPagerFragment(): ViewPagerFragment

    @ContributesAndroidInjector
    abstract fun page1Fragment(): Page1Fragment

    @ContributesAndroidInjector
    abstract fun page2Fragment(): Page2Fragment

    @ContributesAndroidInjector
    abstract fun viewPagerLottieFragment(): ViewPagerLottieFragment
}
