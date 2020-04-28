package ar.com.wolox.android.cookbook.tagsfilters

import ar.com.wolox.android.cookbook.datasync.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TagService {

    @GET("type/")
    fun getTags(): Call<TagPokemonType>

    @GET("{typeUrl}")
    fun getPokemonListByType(@Path("typeUrl") url: String) : Call<List<Pokemon>>

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}