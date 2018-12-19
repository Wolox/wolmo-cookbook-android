package ar.com.wolox.android.cookbook.datasync

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Service to interact with Pokemon API.
 */
interface PokemonService {

    @GET("/api/v2/pokemon/{name}/")
    fun findByName(@Path("name") pokemonName: String): Call<Pokemon>
}
