package ar.com.wolox.android.cookbook.coroutines

import ar.com.wolox.android.cookbook.coroutines.core.CoroutineBasePresenter
import ar.com.wolox.android.cookbook.coroutines.networking.FootbalRepository
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutinesRecipePresenter @Inject constructor(
    private val footbalRepository: FootbalRepository
) : CoroutineBasePresenter<CoroutinesRecipeView>() {

    override fun onViewAttached() = launch {
        val competition = footbalRepository.getCompetitionAndTeams(SPAIN_COMPETITION_ID).run {
            copy(teams = teams
                    .shuffled()
                    .take(MAX_REQUESTS)
                    .mapCooperative { footbalRepository.getTeam(it.id) }
                    .orEmpty())
        }

        view.showCompetition(competition)
    }.unit

    private val <T> T.unit: Unit
        get() = Unit

    inline fun <T, R> Iterable<T>.mapCooperative(transform: (T) -> R): List<R>? = run {
        map {
            if (!isActive) {
                return@run null
            }
            transform(it)
        }
    }

    companion object {
        private const val SPAIN_COMPETITION_ID: Long = 2014
        private const val MAX_REQUESTS = 2
    }
}