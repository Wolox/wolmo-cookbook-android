package ar.com.wolox.android.cookbook.mercadopago.network

import javax.inject.Inject

/**
 * Repository that contains the products.
 */
class MercadoPagoProductsRepository @Inject constructor(private val mercadoPagoProductsService: MercadoPagoProductsService) {

    /** Return the list of all the available products. */
    suspend fun getProducts() = mercadoPagoProductsService.getProducts()
}