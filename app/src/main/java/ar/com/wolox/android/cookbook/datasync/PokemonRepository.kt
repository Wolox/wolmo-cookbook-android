package ar.com.wolox.android.cookbook.datasync

import ar.com.wolox.android.cookbook.common.java8.Empty
import ar.com.wolox.android.cookbook.common.java8.Optional
import ar.com.wolox.android.cookbook.common.network.networkCallback
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * Provided with a [PokemonService], the [PokemonRepository] allows the user to query [Pokemon] by
 * name and also the last found.
 *
 * The last found pokemon is cached so querying it, without a [findByName] call in between, should
 * yield the same result from memory.
 *
 * Users of this class must subscribe to [lastPokemon] to observe query results.
 */
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