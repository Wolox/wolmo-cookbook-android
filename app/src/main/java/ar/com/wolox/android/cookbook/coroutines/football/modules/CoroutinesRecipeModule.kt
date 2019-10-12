package ar.com.wolox.android.cookbook.coroutines.football.modules

import ar.com.wolox.android.cookbook.coroutines.football.CoroutinesRecipeActivity
import ar.com.wolox.android.cookbook.coroutines.football.CoroutinesRecipeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoroutinesRecipeModule {

    @ContributesAndroidInjector
    abstract fun CoroutinesRecipeActivity(): CoroutinesRecipeActivity

    @CoroutinesRecipeScope
    @ContributesAndroidInjector(modules = [FootballModule::class])
    abstract fun CoroutinesRecipeFragment(): CoroutinesRecipeFragment
}