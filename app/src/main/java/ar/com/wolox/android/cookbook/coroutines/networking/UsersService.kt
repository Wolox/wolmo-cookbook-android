package ar.com.wolox.android.cookbook.coroutines.networking

import ar.com.wolox.android.cookbook.coroutines.model.CoroutinesUser
import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {

    /**
     * Returns a list of the users from the API that have the given email and password (normally just once).
     * It simulates the need to send a token.
     */
    @GET("/users")
    suspend fun getUser(
            @Query("token") token: String,
            @Query("email") email: String,
            @Query("password") password: String
    ): List<CoroutinesUser>
}

/**
 * Extension function that adds the possibility to work without worrying about the token.
 * It expects a [function] that will run in the [UsersService] context and accepting the token as parameter.
 */
suspend inline fun <T> UsersService.withToken(function: UsersService.(token: String) -> T): T {
    val token = requestToken()
    val result = function(token)
    releaseToken(token)
    return result
}

/**
 * Hardcoded function to simulate an api call to request a token.
 * This method shouldn't be an extension if it's implemented on the real life.
 */
suspend fun UsersService.requestToken(): String {
    delay(1_500)
    return "2131312312dsaknbsfagh23miu2r2wfsmdnzbxvsq3249aicnstjxgfsdofñijad<"
}

/**
 * Hardcoded function to simulate an api call to release a token.
 * This method shouldn't be an extension if it's implemented on the real life.
 */
suspend fun UsersService.releaseToken(token: String) = delay(1_000)