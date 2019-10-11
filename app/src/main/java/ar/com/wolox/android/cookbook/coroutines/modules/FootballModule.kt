package ar.com.wolox.android.cookbook.coroutines.modules

import ar.com.wolox.android.cookbook.coroutines.networking.FootbalRepository
import ar.com.wolox.android.cookbook.coroutines.networking.FootballService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object FootballModule {
    private const val BASE_URL = "http://api.football-data.org/"

    @JvmStatic
    @CoroutinesRecipeScope
    @Provides
    internal fun providesBooksRepository(footballService: FootballService) = FootbalRepository(footballService)

    @JvmStatic
    @CoroutinesRecipeScope
    @Provides
    internal fun providesBooksService(retrofit: Retrofit): FootballService {
        return retrofit.newBuilder().baseUrl(BASE_URL).build().create(FootballService::class.java)
    }
}
