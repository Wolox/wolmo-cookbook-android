package ar.com.wolox.android.cookbook.datasync

import retrofit2.Call
import retrofit2.http.GET

/**
 * Service to interact with Pokemon API.
 */
interface PokemonService {

    @get:GET("/api/v2/pokemon/charizard/")
    val charizard: Call<Pokemon>
}
