package ar.com.wolox.android.cookbook.common.di.modules

import ar.com.wolox.android.cookbook.instagramlogin.InstagramLoginRecipeActivity
import ar.com.wolox.android.cookbook.instagramlogin.InstagramLoginRecipeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InstagramModule {

    @ContributesAndroidInjector
    abstract fun instagramLoginActivity(): InstagramLoginRecipeActivity

    @ContributesAndroidInjector
    abstract fun instagramLoginFragment(): InstagramLoginRecipeFragment
}