package ar.com.wolox.android.cookbook.common.di.modules

import ar.com.wolox.android.cookbook.twitterlogin.TwitterLoginRecipeActivity
import ar.com.wolox.android.cookbook.twitterlogin.TwitterLoginRecipeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TwitterModule {

    @ContributesAndroidInjector
    abstract fun twitterLoginActivity(): TwitterLoginRecipeActivity

    @ContributesAndroidInjector
    abstract fun twitterLoginFragment(): TwitterLoginRecipeFragment
}