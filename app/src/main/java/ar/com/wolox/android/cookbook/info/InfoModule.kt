package ar.com.wolox.android.cookbook.info

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InfoModule {

    @ContributesAndroidInjector
    abstract fun infoActivity(): InfoActivity

    @ContributesAndroidInjector
    abstract fun infoFragment(): InfoFragment
}
