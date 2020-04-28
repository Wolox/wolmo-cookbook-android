package ar.com.wolox.android.cookbook.mercadopago.network

import ar.com.wolox.android.cookbook.mercadopago.model.Product
import retrofit2.http.GET

/**
 * API endpoints for the products.
 */
interface MercadoPagoProductsService {

    /** List all the products. */
    @GET("/product")
    suspend fun getProducts(): List<Product>
}
