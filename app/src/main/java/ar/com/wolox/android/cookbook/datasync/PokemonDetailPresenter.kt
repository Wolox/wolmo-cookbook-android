package ar.com.wolox.android.cookbook.datasync

import ar.com.wolox.android.cookbook.common.java8.Empty
import ar.com.wolox.android.cookbook.common.java8.Just
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named

class PokemonDetailPresenter @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    @Named("main") private val mainThreadScheduler: Scheduler,
    @Named("background") private val backgroundThreadScheduler: Scheduler
) : BasePresenter<PokemonDetailView>() {

    private var lastPokemonDisposable: Disposable? = null

    override fun onViewAttached() {
        super.onViewAttached()

        lastPokemonDisposable = pokemonRepository.lastPokemon
                .subscribeOn(backgroundThreadScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe {
                    when (it) {
                        is Just<Pokemon> -> view?.showPokemonDetail(it.value)
                        is Empty -> view?.showNoPokemon()
                    }
                }
    }

    override fun onViewDetached() {
        lastPokemonDisposable?.apply { if (!isDisposed) dispose() }
        super.onViewDetached()
    }
}
