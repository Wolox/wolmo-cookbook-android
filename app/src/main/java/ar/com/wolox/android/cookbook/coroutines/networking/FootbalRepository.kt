package ar.com.wolox.android.cookbook.coroutines.networking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository to fetch football information from football data API.
 * @see <a href="https://www.football-data.org/documentation/quickstart">Documentation</a>
 */
class FootbalRepository(private val footballService: FootballService) {

    suspend fun getCompetitionAndTeams(competitionId: Long) = withContext(Dispatchers.IO) {
        footballService.getCompetitionAndTeams(competitionId)
    }

    suspend fun getTeam(teamId: Long) = withContext(Dispatchers.IO) {
        footballService.getTeam(teamId)
    }
}