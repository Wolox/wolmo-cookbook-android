package ar.com.wolox.android.cookbook.coroutines

import ar.com.wolox.android.cookbook.coroutines.core.CoroutineBasePresenter
import ar.com.wolox.android.cookbook.coroutines.core.mapCooperative
import ar.com.wolox.android.cookbook.coroutines.core.unit
import ar.com.wolox.android.cookbook.coroutines.networking.CoroutineFootballRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutinesRecipePresenter @Inject constructor(
    private val footballRepository: CoroutineFootballRepository
) : CoroutineBasePresenter<CoroutinesRecipeView>() {

    override fun onViewAttached() = launch { view.showCompetition(fetchCompetition()) }.unit

    private suspend fun fetchCompetition() = footballRepository.getCompetition(SPAIN_COMPETITION_ID).run {
        copy(teams = teams
                .shuffled()
                .take(MAX_REQUESTS)
                .mapCooperative { footballRepository.getTeam(it.id) }
                .orEmpty())
    }

    companion object {
        private const val SPAIN_COMPETITION_ID: Long = 2014
        private const val MAX_REQUESTS = 2
    }
}