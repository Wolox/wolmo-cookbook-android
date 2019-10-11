package ar.com.wolox.android.cookbook.coroutines.networking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FootbalRepository(private val footballService: FootballService) {

    suspend fun getCompetitionAndTeams(competitionId: Long) = withContext(Dispatchers.IO) {
        footballService.getCompetitionAndTeams(competitionId)
    }

    suspend fun getTeam(teamId: Long) = withContext(Dispatchers.IO) {
        footballService.getTeam(teamId)
    }
}