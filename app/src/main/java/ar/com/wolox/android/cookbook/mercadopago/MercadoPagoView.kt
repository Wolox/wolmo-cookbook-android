package ar.com.wolox.android.cookbook.mercadopago

import ar.com.wolox.android.cookbook.mercadopago.model.Product
import com.mercadopago.android.px.core.MercadoPagoCheckout

interface MercadoPagoView {

    fun startLoading()

    fun finishLoading()

    fun payProduct(checkout: MercadoPagoCheckout)

    fun proceedResult(message: String)

    fun showErrorMessage(errorMessage: String)

    fun showCanceledMessage()

    fun showProducts(items: List<Pair<Product, Int>>)

    fun showEmptyNameError()

    fun showEmptyEmailError()

    fun showInvalidEmailError()

    fun showUnexpectedError()

    fun showTotal(total: Float)
}