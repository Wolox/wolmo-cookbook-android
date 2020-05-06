package ar.com.wolox.android.cookbook.mercadopago.handler

import com.mercadopago.android.px.model.Payment
import com.mercadopago.android.px.model.exceptions.MercadoPagoError

interface MercadoPagoResultListener {

    fun onSuccess(paymentResult: Payment)

    fun onMercadoPagoError(mercadoPagoError: MercadoPagoError)

    fun onError(errorMessage: String)

    fun onCanceled()
}