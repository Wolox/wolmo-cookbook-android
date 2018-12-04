package ar.com.wolox.android.cookbook.datasync

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class PokemonSearchPresenter @Inject constructor(private val pokemonRepository: PokemonRepository)
    : BasePresenter<PokemonSearchView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        // TODO: Implement
    }

}