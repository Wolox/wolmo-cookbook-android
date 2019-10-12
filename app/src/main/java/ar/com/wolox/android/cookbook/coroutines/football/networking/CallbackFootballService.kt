package ar.com.wolox.android.cookbook.coroutines.football.networking

import ar.com.wolox.android.cookbook.coroutines.football.model.Competition
import ar.com.wolox.android.cookbook.coroutines.football.model.Team
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CallbackFootballService {

    @GET("/v2/competitions/{id}/teams")
    @Headers("X-Auth-Token: $API_KEY")
    fun getCompetition(@Path("id") id: Long): Call<Competition>

    @GET("/v2/teams/{id}")
    @Headers("X-Auth-Token: $API_KEY")
    fun getTeam(@Path("id") id: Long): Call<Team>

    companion object {
        const val API_KEY = "8952a61e814843d3a64818c22a320309"
    }
}
