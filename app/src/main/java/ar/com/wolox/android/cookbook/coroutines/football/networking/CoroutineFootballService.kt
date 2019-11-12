package ar.com.wolox.android.cookbook.coroutines.football.networking

import ar.com.wolox.android.cookbook.coroutines.football.model.Team
import ar.com.wolox.android.cookbook.coroutines.football.networking.responses.CompetitionResponse
import ar.com.wolox.android.cookbook.coroutines.football.networking.responses.MatchesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CoroutineFootballService {

    @GET("/v2/competitions/{id}/teams")
    @Headers("X-Auth-Token: $API_KEY")
    suspend fun getCompetition(@Path("id") id: Long): CompetitionResponse

    @GET("/v2/teams/{id}")
    @Headers("X-Auth-Token: $API_KEY")
    suspend fun getTeam(@Path("id") id: Long): Team

    @GET("/v2/players/{id}/matches")
    @Headers("X-Auth-Token: $API_KEY")
    suspend fun getMatches(@Path("id") id: Long): MatchesResponse

    companion object {
        const val API_KEY = "8952a61e814843d3a64818c22a320309"
    }
}
