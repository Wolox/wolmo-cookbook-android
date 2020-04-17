package ar.com.wolox.android.cookbook.mercadopago.network

import ar.com.wolox.android.cookbook.mercadopago.model.Cart
import ar.com.wolox.android.cookbook.mercadopago.model.CheckoutPreference
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * API endpoints for Mercado Pago stuff.
 */
interface MercadoPagoService {

    /** Post a new [cart] checkout. */
    @POST("/checkout")
    suspend fun checkout(@Body cart: Cart): CheckoutPreference
}
