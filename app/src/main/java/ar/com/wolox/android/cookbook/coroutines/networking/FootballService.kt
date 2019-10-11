package ar.com.wolox.android.cookbook.coroutines.networking

import ar.com.wolox.android.cookbook.coroutines.model.Competition
import ar.com.wolox.android.cookbook.coroutines.model.Team
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FootballService {

    /** Returns a list of the teams from the API. */
    @GET("/v2/competitions/{id}/teams")
    @Headers("X-Auth-Token: $API_KEY")
    suspend fun getCompetitionAndTeams(@Path("id") id: Long): Competition

    @GET("/v2/teams/{id}")
    @Headers("X-Auth-Token: $API_KEY")
    suspend fun getTeam(@Path("id") id: Long): Team

    companion object {
        const val API_KEY = "8952a61e814843d3a64818c22a320309"
    }
}
