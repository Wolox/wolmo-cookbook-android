package ar.com.wolox.android.cookbook.coroutines

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoroutinesRecipeModule {

    @ContributesAndroidInjector
    abstract fun CoroutinesRecipeActivity(): CoroutinesRecipeActivity

    @ContributesAndroidInjector
    abstract fun CoroutinesRecipeFragment(): CoroutinesRecipeFragment
}