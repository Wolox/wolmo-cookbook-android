package ar.com.wolox.android.cookbook.mercadopago.network

import ar.com.wolox.android.cookbook.mercadopago.model.Cart
import javax.inject.Inject

/**
 * This adapter is the responsible to manage the connections to the [mercadoPagoService].
 */
class MercadoPagoAdapter @Inject constructor(private val mercadoPagoService: MercadoPagoService) {

    /** The checkout means that the backend will generate a new payment preference by the given [cart]. */
    suspend fun checkout(cart: Cart) = try {
        mercadoPagoService.checkout(cart)
    } catch (e: Exception) {
        // Here we should do something if we want to handle the different posible errors.
        null
    }
}
