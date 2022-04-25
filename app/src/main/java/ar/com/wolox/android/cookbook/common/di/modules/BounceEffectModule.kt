package ar.com.wolox.android.cookbook.common.di.modules

import ar.com.wolox.android.cookbook.bounce.BounceEffectActivity
import ar.com.wolox.android.cookbook.bounce.BounceEffectFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BounceEffectModule {

    @ContributesAndroidInjector
    abstract fun bounceActivity(): BounceEffectActivity

    @ContributesAndroidInjector
    abstract fun bounceFragment(): BounceEffectFragment
}