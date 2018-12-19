package ar.com.wolox.android.cookbook.common.di

import ar.com.wolox.wolmo.networking.di.modules.GsonModule
import ar.com.wolox.wolmo.networking.di.modules.NetworkingModule
import ar.com.wolox.wolmo.networking.di.modules.OkHttpClientModule
import ar.com.wolox.wolmo.networking.di.scopes.NetworkingScope
import ar.com.wolox.wolmo.networking.utils.GsonTypeAdapter
import com.google.gson.FieldNamingPolicy
import dagger.BindsInstance
import dagger.Component
import okhttp3.Interceptor
import retrofit2.Retrofit

@NetworkingScope
@Component(modules = [GsonModule::class, OkHttpClientModule::class, NetworkingModule::class])
interface CookbookNetworkingComponent {
    fun retrofit(): Retrofit

    @dagger.Component.Builder
    interface Builder {
        @BindsInstance
        fun baseUrl(baseUrl: String): CookbookNetworkingComponent.Builder

        @BindsInstance
        fun okHttpInterceptors(interceptors: Array<Interceptor>?): CookbookNetworkingComponent.Builder

        @BindsInstance
        fun gsonNamingPolicy(policy: FieldNamingPolicy): CookbookNetworkingComponent.Builder

        @BindsInstance
        fun gsonTypeAdapters(adapters: Array<GsonTypeAdapter>?): CookbookNetworkingComponent.Builder

        fun build(): CookbookNetworkingComponent
    }
}