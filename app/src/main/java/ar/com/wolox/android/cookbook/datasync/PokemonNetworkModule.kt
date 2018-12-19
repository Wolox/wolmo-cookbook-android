package ar.com.wolox.android.cookbook.datasync

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PokemonNetworkModule {

    @Module
    companion object {

        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        @JvmStatic
        @DataSyncScope
        @Provides
        internal fun providesPokemonService(retrofit: Retrofit): PokemonService {
            return retrofit.newBuilder().baseUrl(BASE_URL).build().create(PokemonService::class.java)
        }

        @JvmStatic
        @DataSyncScope
        @Provides
        internal fun providesPokemonRepository(pokemonService: PokemonService): PokemonRepository {
            return PokemonRepository(pokemonService)
        }
    }
}
