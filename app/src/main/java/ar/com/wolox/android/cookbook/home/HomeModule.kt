package ar.com.wolox.android.cookbook.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @ContributesAndroidInjector
    abstract fun homehActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment
}
