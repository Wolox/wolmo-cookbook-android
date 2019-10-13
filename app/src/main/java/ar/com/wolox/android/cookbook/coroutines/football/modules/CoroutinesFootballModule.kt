package ar.com.wolox.android.cookbook.coroutines.football.modules

import ar.com.wolox.android.cookbook.coroutines.football.FootballActivity
import ar.com.wolox.android.cookbook.coroutines.football.FootballFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoroutinesFootballModule {

    @ContributesAndroidInjector
    abstract fun CoroutinesRecipeActivity(): FootballActivity

    @CoroutinesFootbalScope
    @ContributesAndroidInjector(modules = [FootballModule::class])
    abstract fun CoroutinesRecipeFragment(): FootballFragment
}