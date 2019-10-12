package ar.com.wolox.android.cookbook.coroutines.examples

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoroutinesExampleModule {

    @ContributesAndroidInjector
    abstract fun coroutinesExampleActivity(): CoroutinesExampleActivity

    @ContributesAndroidInjector()
    abstract fun coroutinesExampleFragment(): CoroutinesExampleFragment
}