package ar.com.wolox.android.cookbook.coroutines.football

import ar.com.wolox.android.cookbook.coroutines.core.CoroutineBasePresenter
import ar.com.wolox.android.cookbook.coroutines.core.mapCooperative
import ar.com.wolox.android.cookbook.coroutines.core.unit
import ar.com.wolox.android.cookbook.coroutines.football.networking.CoroutineFootballRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutinesFootballPresenter @Inject constructor(
    private val footballRepository: CoroutineFootballRepository
) : CoroutineBasePresenter<CoroutinesFootballView>() {

    override fun onViewAttached() = launch { view.showCompetition(fetchCompetitionAsync()) }.unit

    private suspend fun fetchCompetition() = footballRepository.getCompetition(SPAIN_COMPETITION_ID).run {
        copy(teams = teams
                .shuffled()
                .take(MAX_REQUESTS)
                .mapCooperative { footballRepository.getTeam(it.id) }
                .orEmpty())
    }

    private suspend fun fetchCompetitionAsync() = footballRepository.getCompetition(SPAIN_COMPETITION_ID).run {
        copy(teams = teams
                .shuffled()
                .take(MAX_REQUESTS)
                .mapCooperative { async { footballRepository.getTeam(it.id) } }
                .orEmpty()
                .awaitAll())
    }

    companion object {
        private const val SPAIN_COMPETITION_ID: Long = 2014
        private const val MAX_REQUESTS = 2
    }
}