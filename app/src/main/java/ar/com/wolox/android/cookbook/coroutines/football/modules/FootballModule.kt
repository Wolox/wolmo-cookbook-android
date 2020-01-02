package ar.com.wolox.android.cookbook.coroutines.football.modules

import ar.com.wolox.android.cookbook.coroutines.football.networking.CallbackFootballRepository
import ar.com.wolox.android.cookbook.coroutines.football.networking.CallbackFootballService
import ar.com.wolox.android.cookbook.coroutines.football.networking.CallbackToCoroutineFootballRepository
import ar.com.wolox.android.cookbook.coroutines.football.networking.CoroutineFootballRepository
import ar.com.wolox.android.cookbook.coroutines.football.networking.CoroutineFootballService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
object FootballModule {

    private const val BASE_URL = "http://api.football-data.org/"

    @JvmStatic
    @Provides
    @Named("football")
    internal fun providesCoroutineFootballRetrofit(retrofit: Retrofit): Retrofit {
        val dateFormatter = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create()
        return retrofit
                .newBuilder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(dateFormatter))
                .build()
    }

    @JvmStatic
    @Provides
    internal fun providesCoroutineFootballService(@Named("football") retrofit: Retrofit): CoroutineFootballService {
        return retrofit.create(CoroutineFootballService::class.java)
    }

    @JvmStatic
    @Provides
    internal fun providesCoroutineFootballRepository(footballService: CoroutineFootballService): CoroutineFootballRepository {
        return CoroutineFootballRepository(footballService)
    }

    @JvmStatic
    @Provides
    internal fun providesCallbackToCoroutinesFootballRespository(footballService: CallbackFootballService): CallbackToCoroutineFootballRepository {
        return CallbackToCoroutineFootballRepository(footballService)
    }

    @JvmStatic
    @Provides
    internal fun providesCallbackFootballService(@Named("football") retrofit: Retrofit): CallbackFootballService {
        return retrofit.create(CallbackFootballService::class.java)
    }

    @JvmStatic
    @Provides
    internal fun providesCallbackFootballRepository(footballService: CallbackFootballService): CallbackFootballRepository {
        return CallbackFootballRepository(footballService)
    }
}
