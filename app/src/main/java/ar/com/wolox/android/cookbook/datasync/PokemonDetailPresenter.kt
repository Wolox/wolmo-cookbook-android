package ar.com.wolox.android.cookbook.datasync

import ar.com.wolox.android.cookbook.common.network.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class PokemonDetailPresenter @Inject constructor(private val pokemonService: PokemonService)
    : BasePresenter<PokemonDetailView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        view.showNoPokemon()

        pokemonService
                .charizard
                .enqueue(networkCallback {
                    onResponseSuccessful {
                        if (it != null) {
                            view.showPokemon(it)
                            return@onResponseSuccessful
                        }
                    }
                })
    }
}
