package ar.com.wolox.android.cookbook.mercadopago.model

/**
 * Representation of an order cart.
 * It can only be initialized with a valid [client] and a non-empty list of [products].
 */
data class Cart(val client: Client, val products: List<ProductQuantity>) {

    init {
        require(products.isNotEmpty()) { "Cart can only be initialized with a non-empty list of products" }
    }
}
