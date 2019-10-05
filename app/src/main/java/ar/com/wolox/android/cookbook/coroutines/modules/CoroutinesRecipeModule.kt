package ar.com.wolox.android.cookbook.coroutines.modules

import ar.com.wolox.android.cookbook.coroutines.CoroutinesRecipeActivity
import ar.com.wolox.android.cookbook.coroutines.CoroutinesRecipeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoroutinesRecipeModule {

    @ContributesAndroidInjector
    abstract fun CoroutinesRecipeActivity(): CoroutinesRecipeActivity

    @CoroutinesScope
    @ContributesAndroidInjector(modules = [CoroutinesUsersNetworkModule::class])
    abstract fun CoroutinesRecipeFragment(): CoroutinesRecipeFragment
}