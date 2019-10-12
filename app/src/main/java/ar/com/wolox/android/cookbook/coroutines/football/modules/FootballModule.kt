package ar.com.wolox.android.cookbook.coroutines.football.modules

import ar.com.wolox.android.cookbook.coroutines.football.networking.CallbackFootballRepository
import ar.com.wolox.android.cookbook.coroutines.football.networking.CallbackFootballService
import ar.com.wolox.android.cookbook.coroutines.football.networking.CoroutineFootballRepository
import ar.com.wolox.android.cookbook.coroutines.football.networking.CoroutineFootballService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object FootballModule {
    private const val BASE_URL = "http://api.football-data.org/"

    @JvmStatic
    @CoroutinesRecipeScope
    @Provides
    internal fun providesCoroutineFootballService(retrofit: Retrofit): CoroutineFootballService {
        return retrofit.newBuilder().baseUrl(BASE_URL).build().create(CoroutineFootballService::class.java)
    }

    @JvmStatic
    @CoroutinesRecipeScope
    @Provides
    internal fun providesCoroutineFootballRepository(footballService: CoroutineFootballService) = CoroutineFootballRepository(footballService)

    @JvmStatic
    @CoroutinesRecipeScope
    @Provides
    internal fun providesCallbackFootballService(retrofit: Retrofit): CallbackFootballService {
        return retrofit.newBuilder().baseUrl(BASE_URL).build().create(CallbackFootballService::class.java)
    }

    @JvmStatic
    @CoroutinesRecipeScope
    @Provides
    internal fun providesCallbackFootballRepository(footballService: CallbackFootballService) = CallbackFootballRepository(footballService)
}
