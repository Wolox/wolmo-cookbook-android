package ar.com.wolox.android.cookbook.mercadopago.model

import ar.com.wolox.android.cookbook.common.utils.isValidEmail

/**
 * Representation of an order client.
 */
data class Client(val name: String, val email: String) {

    init {
        require(email.isValidEmail) { "The given email doesn't have a valid format" }
    }
}
