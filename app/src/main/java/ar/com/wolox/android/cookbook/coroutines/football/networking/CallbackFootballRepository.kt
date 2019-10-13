package ar.com.wolox.android.cookbook.coroutines.football.networking

import ar.com.wolox.android.cookbook.common.network.SimpleNetworkCallback
import ar.com.wolox.android.cookbook.common.network.networkCallback
import ar.com.wolox.android.cookbook.coroutines.football.model.Competition
import ar.com.wolox.android.cookbook.coroutines.football.model.Match
import ar.com.wolox.android.cookbook.coroutines.football.model.Team

/**
 * Repository to fetch football information from football data API.
 * @see <a href="https://www.football-data.org/documentation/quickstart">Documentation</a>
 */
class CallbackFootballRepository(private val footballService: CallbackFootballService) {

    fun getCompetition(competitionId: Long, callback: SimpleNetworkCallback<Competition>) {
        footballService.getCompetition(competitionId).enqueue(networkCallback {

            onResponseSuccessful { competitionResponse ->
                callback.onResponseSuccessful(competitionResponse?.let {
                    Competition(it.data.id, it.data.name, it.teams)
                })
            }

            onResponseFailed(callback::onResponseFailed)

            onResponseFailed(callback::onResponseFailed)
        })
    }

    fun getTeam(teamId: Long, callback: SimpleNetworkCallback<Team>) {
        footballService.getTeam(teamId).enqueue(callback)
    }

    fun getMatches(playerId: Long, callback: SimpleNetworkCallback<List<Match>>) {
        footballService.getMatches(playerId).enqueue(networkCallback {

            onResponseSuccessful { callback.onResponseSuccessful(it?.matches) }

            onResponseFailed(callback::onResponseFailed)

            onResponseFailed(callback::onResponseFailed)
        })
    }
}