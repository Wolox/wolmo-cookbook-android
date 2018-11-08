package ar.com.wolox.android.cookbook.datasync

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DataSyncRecipeModule {

    @DataSyncScope
    @ContributesAndroidInjector
    abstract fun dataSyncRecipeActivity(): DataSyncRecipeActivity

    @DataSyncScope
    @ContributesAndroidInjector(modules = [PokemonNetworkModule::class])
    abstract fun dataSyncRecipeFragment(): DataSyncRecipeFragment
}