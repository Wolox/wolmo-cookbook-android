package ar.com.wolox.android.cookbook.mercadopago

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