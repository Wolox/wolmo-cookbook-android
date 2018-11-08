package ar.com.wolox.android.cookbook.datasync

import android.util.Log
import ar.com.wolox.android.cookbook.common.network.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class DataSyncRecipePresenter @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonService2: PokemonService
)
    : BasePresenter<DataSyncRecipeView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        Log.d("DataSyncRecipe", "Pokemon Service: $pokemonService")
        Log.d("DataSyncRecipe", "Pokemon Service: $pokemonService2")

        pokemonService
                .charizard
                .enqueue(networkCallback {
                    onResponseSuccessful {
                        if (it != null) {
                            view.showPokemon(it)
                            return@onResponseSuccessful
                        }

                        view.showError()
                    }

                    onResponseFailed { _, _ ->
                        view.showError()
                    }
                })
    }
}
