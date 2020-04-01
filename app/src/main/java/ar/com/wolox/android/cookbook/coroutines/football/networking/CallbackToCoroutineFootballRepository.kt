package ar.com.wolox.android.cookbook.coroutines.football.networking

import ar.com.wolox.android.cookbook.common.network.networkCallback
import ar.com.wolox.android.cookbook.coroutines.football.model.Competition
import ar.com.wolox.android.cookbook.coroutines.football.model.Team
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Repository to fetch football information from football data API.
 * @see <a href="https://www.football-data.org/documentation/quickstart">Documentation</a>
 */
class CallbackToCoroutineFootballRepository(private val footballService: CallbackFootballService) {

    /** What should we do to convert a Callback into a suspend function */
    suspend fun _getCompetition(competitionId: Long) = suspendCoroutine<Competition> { continuation ->
        footballService.getCompetition(competitionId).enqueue(networkCallback {

            onResponseSuccessful { competitionResponse ->
                competitionResponse?.let {
                    continuation.resume(Competition(it.data.id, it.data.name, it.teams))
                }
            }

            onResponseFailed { body, code ->
                val response = Response.error<Competition>(code, body!!)
                continuation.resumeWithException(HttpException(response))
            }

            onCallFailure { exception ->
                continuation.resumeWithException(exception!!)
            }
        })
    }

    /** Using a function that converts a retrofit call into a suspend function. */
    suspend fun getCompetition(competitionId: Long): Competition {
        val competitionResponse = footballService.getCompetition(competitionId).enqueueSuspended()
        return Competition(competitionResponse.data.id, competitionResponse.data.name, competitionResponse.teams)
    }

    suspend fun getTeam(teamId: Long): Team {
        return footballService.getTeam(teamId).enqueueSuspended()
    }

    suspend fun getMatches(playerId: Long) = footballService.getMatches(playerId).enqueueSuspended().matches

    private suspend inline fun <T> Call<T>.enqueueSuspended() = suspendCoroutine<T> { continuation ->
        enqueue(networkCallback<T> {
            onResponseSuccessful {
                if (it != null) {
                    continuation.resume(it)
                } else {
                    continuation.resumeWithException(NullableResponse())
                }
            }

            onResponseFailed { responseBody, code ->
                if (responseBody != null) {
                    continuation.resumeWithException(HttpException(Response.error<T>(code, responseBody)))
                } else {
                    continuation.resumeWithException(NullableResponse())
                }
            }

            onCallFailure { exception ->
                if (exception != null) {
                    continuation.resumeWithException(exception)
                } else {
                    continuation.resumeWithException(NullableResponse())
                }
            }
        })
    }

    class NullableResponse : Exception()
}