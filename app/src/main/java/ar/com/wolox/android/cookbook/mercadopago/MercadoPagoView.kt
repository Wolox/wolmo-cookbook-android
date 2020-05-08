package ar.com.wolox.android.cookbook.mercadopago

import ar.com.wolox.android.cookbook.mercadopago.model.Product
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.model.Payment

interface MercadoPagoView {

    fun startLoading()

    fun finishLoading()

    fun payProduct(checkout: MercadoPagoCheckout)

    fun showPaymentSuccessResult(payment: Payment)

    fun showErrorMessage(errorMessage: String)

    fun showCanceledMessage()

    fun showProducts(items: List<Pair<Product, Int>>)

    fun showEmptyNameError()

    fun showEmptyEmailError()

    fun showInvalidEmailError()

    fun showUnexpectedError()

    fun showTotal(total: Float)
}