package ar.com.wolox.android.cookbook.datasync

import ar.com.wolox.android.cookbook.common.java8.Empty
import ar.com.wolox.android.cookbook.common.java8.Just
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class PokemonSearchPresenter @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    @Named("main") private val mainThreadScheduler: Scheduler,
    @Named("background") private val backgroundThreadScheduler: Scheduler
) : BasePresenter<PokemonSearchView>() {

    private var lastPokemonDisposable: Disposable? = null
    private var searchPokemonDisposable: Disposable? = null
    private var searchedPokemonName: String = ""

    override fun onViewAttached() {
        super.onViewAttached()

        lastPokemonDisposable = pokemonRepository.lastPokemon
                .subscribeOn(backgroundThreadScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe {
                    when (it) {
                        is Just<Pokemon> -> view.showPokemonFound(it.value)
                        is Empty -> view.showNoPokemonFound()
                    }
                }
    }

    fun onSearchNameChanged(newName: String) {
        searchedPokemonName = newName.trim().toLowerCase()

        searchPokemonDisposable?.apply { if (!isDisposed) dispose() }
        searchPokemonDisposable = backgroundThreadScheduler.scheduleDirect({
            pokemonRepository.findByName(searchedPokemonName)
        }, 1L, TimeUnit.SECONDS)
    }

    override fun onViewDetached() {
        lastPokemonDisposable?.apply { if (!isDisposed) dispose() }
        searchPokemonDisposable?.apply { if (!isDisposed) dispose() }

        super.onViewDetached()
    }
}