package ar.com.wolox.android.cookbook.coroutines.networking

import ar.com.wolox.android.cookbook.coroutines.model.Competition
import ar.com.wolox.android.cookbook.coroutines.model.Team
import retrofit2.Callback

/**
 * Repository to fetch football information from football data API.
 * @see <a href="https://www.football-data.org/documentation/quickstart">Documentation</a>
 */
class CallbackFootballRepository(private val footballService: CallbackFootballService) {

    fun getCompetition(competitionId: Long, callback: Callback<Competition>) {
        footballService.getCompetition(competitionId).enqueue(callback)
    }

    fun getTeam(teamId: Long, callback: Callback<Team>) {
        footballService.getTeam(teamId).enqueue(callback)
    }
}