package ar.com.wolox.android.cookbook.coroutines.football.modules

import ar.com.wolox.android.cookbook.coroutines.football.CoroutinesFootballActivity
import ar.com.wolox.android.cookbook.coroutines.football.CoroutinesFootballFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoroutinesFootballModule {

    @ContributesAndroidInjector
    abstract fun CoroutinesRecipeActivity(): CoroutinesFootballActivity

    @CoroutinesFootbalScope
    @ContributesAndroidInjector(modules = [FootballModule::class])
    abstract fun CoroutinesRecipeFragment(): CoroutinesFootballFragment
}