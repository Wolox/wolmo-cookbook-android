package ar.com.wolox.android.cookbook.mercadopago.di

import ar.com.wolox.android.cookbook.mercadopago.network.MercadoPagoProductsService
import ar.com.wolox.android.cookbook.mercadopago.network.MercadoPagoService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
object MercadoPagoServicesModule {

    private const val BASE_URL = "https://mercadopagocookbookbackend.herokuapp.com/"

    @JvmStatic
    @Provides
    @Named("mercadopago")
    internal fun providesMercadoPagoBackendRetrofit(retrofit: Retrofit): Retrofit {
        return retrofit
            .newBuilder()
            .baseUrl(BASE_URL)
            .build()
    }

    @JvmStatic
    @Provides
    internal fun providesMercadoPagoProductsService(@Named("mercadopago") retrofit: Retrofit): MercadoPagoProductsService {
        return retrofit.create(MercadoPagoProductsService::class.java)
    }

    @JvmStatic
    @Provides
    internal fun providesMercadoPagoService(@Named("mercadopago") retrofit: Retrofit): MercadoPagoService {
        return retrofit.create(MercadoPagoService::class.java)
    }
}
