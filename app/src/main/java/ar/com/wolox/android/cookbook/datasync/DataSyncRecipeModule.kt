package ar.com.wolox.android.cookbook.datasync

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DataSyncRecipeModule {

    @DataSyncScope
    @ContributesAndroidInjector(modules = [PokemonNetworkModule::class, PokemonModule::class])
    abstract fun dataSyncRecipeActivity(): DataSyncRecipeActivity
}