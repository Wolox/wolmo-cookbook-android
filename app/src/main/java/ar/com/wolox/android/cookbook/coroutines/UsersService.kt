package ar.com.wolox.android.cookbook.coroutines

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {

    @GET("/users")
    fun getUser(@Query("email") email: String, @Query("password") password: String): Call<List<CoroutinesUser>>
}
