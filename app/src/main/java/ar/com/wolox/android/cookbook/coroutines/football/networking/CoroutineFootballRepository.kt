package ar.com.wolox.android.cookbook.coroutines.football.networking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository to fetch football information from football data API.
 * @see <a href="https://www.football-data.org/documentation/quickstart">Documentation</a>
 */
class CoroutineFootballRepository(private val footballService: CoroutineFootballService) {

    suspend fun getCompetition(competitionId: Long) = withContext(Dispatchers.IO) {
        footballService.getCompetition(competitionId)
    }

    suspend fun getTeam(teamId: Long) = withContext(Dispatchers.IO) {
        footballService.getTeam(teamId)
    }
}