package ar.com.wolox.android.cookbook.common.di.modules

import ar.com.wolox.android.cookbook.lottie.LottieRecipeActivity
import ar.com.wolox.android.cookbook.lottie.LottieRecipeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LottieModule {

    @ContributesAndroidInjector
    abstract fun lottieActivity(): LottieRecipeActivity

    @ContributesAndroidInjector
    abstract fun lottieFragment(): LottieRecipeFragment
}