package ar.com.wolox.android.cookbook.datasync

import ar.com.wolox.android.cookbook.common.java8.Empty
import ar.com.wolox.android.cookbook.common.java8.Optional
import ar.com.wolox.android.cookbook.common.network.networkCallback
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class PokemonRepository(private val pokemonService: PokemonService) {

    // BehaviorSubjects caches last element
    private val foundPokemon = BehaviorSubject.createDefault<Optional<Pokemon>>(Empty)

    val lastPokemon: Observable<Optional<Pokemon>> = foundPokemon

    fun findByName(name: String) {
        pokemonService.findByName(name)
                .enqueue(networkCallback {
                    onResponseSuccessful {
                        foundPokemon.onNext(Optional.ofNullable(it))
                    }

                    onResponseFailed { _, _ ->
                        foundPokemon.onNext(Empty)
                    }

                    onCallFailure {
                        if (it != null) {
                            foundPokemon.onError(it)
                        } else {
                            foundPokemon.onNext(Empty)
                        }
                    }
                })
    }
}