package ar.com.wolox.android.cookbook.coroutines.modules

import ar.com.wolox.android.cookbook.coroutines.networking.CoroutinesUsersRepository
import ar.com.wolox.android.cookbook.coroutines.networking.UsersService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object CoroutinesUsersNetworkModule {
    private const val BASE_URL = "https://android-training.herokuapp.com/"

    @JvmStatic
    @CoroutinesScope
    @Provides
    internal fun providesUsersService(retrofit: Retrofit): UsersService {
        return retrofit.newBuilder().baseUrl(BASE_URL).build().create(UsersService::class.java)
    }

    @JvmStatic
    @CoroutinesScope
    @Provides
    internal fun providesUsersRepository(usersService: UsersService) = CoroutinesUsersRepository(usersService)
}
