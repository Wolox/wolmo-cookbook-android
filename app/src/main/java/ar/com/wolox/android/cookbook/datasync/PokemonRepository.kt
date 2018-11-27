package ar.com.wolox.android.cookbook.datasync

import javax.inject.Inject

@DataSyncScope
class PokemonRepository @Inject constructor(val pokemonService: PokemonService)