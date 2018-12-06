package ar.com.wolox.android.cookbook.datasync

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PokemonModule {

    @ContributesAndroidInjector
    abstract fun pokemonDetailFragment(): PokemonDetailFragment

    @ContributesAndroidInjector
    abstract fun pokemonSearchFragment(): PokemonSearchFragment
}