package ar.com.wolox.android.cookbook.mercadopago

import com.mercadopago.android.px.core.MercadoPagoCheckout

interface MercadoPagoView {

    fun payProduct(checkout: MercadoPagoCheckout)

    fun proceedResult(message: String)
}