package ar.com.wolox.android.cookbook.mercadopago

import ar.com.wolox.android.cookbook.mercadopago.handler.MercadoPagoResultHandler
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MercadoPagoRecipeModule {

    @ContributesAndroidInjector
    abstract fun mercadoPagoActivity(): MercadoPagoRecipeActivity

    @ContributesAndroidInjector
    abstract fun mercadoPagoFragment(): MercadoPagoRecipeFragment

    @ContributesAndroidInjector
    abstract fun mercadoPagoResultHandler(): MercadoPagoResultHandler
}