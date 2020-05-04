package ar.com.wolox.android.cookbook.mercadopago

import android.app.Activity
import android.content.Intent
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.model.Payment
import com.mercadopago.android.px.model.exceptions.MercadoPagoError
import javax.inject.Inject

class MercadoPagoResultHandler @Inject constructor() {

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): String {
        val message = StringBuilder()

        if (requestCode == REQUEST_CODE) {

            if (resultCode == MercadoPagoCheckout.PAYMENT_RESULT_CODE) {
                val payment = data!!.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT) as Payment
                message.append(PAYMENT_WITH_STATUS_MESSAGE)
                        .append(payment)

            } else if (resultCode == Activity.RESULT_CANCELED) {

                if (data != null && data.extras != null && data.extras!!.containsKey(MercadoPagoCheckout.EXTRA_ERROR)) {
                    val mercadoPagoError = data.getSerializableExtra(MercadoPagoCheckout.EXTRA_ERROR) as MercadoPagoError

                    message.append(PAYMENT_ERROR_MESSAGE)
                            .append(mercadoPagoError)

                } else {
                    message.append(PAYMENT_CANCEL_MESSAGE)
                            .append(REQUESTED_CODE_MESSAGE)
                            .append(requestCode)
                            .append(RESULT_CODE_MESSAGE)
                            .append(resultCode)
                }
            } else {
                message.append(REQUESTED_CODE_MESSAGE)
                        .append(requestCode)
                        .append(RESULT_CODE_MESSAGE)
                        .append(resultCode)
            }
        }
        return message.toString()
    }

    companion object {
        private const val REQUEST_CODE = 1
        private const val PAYMENT_WITH_STATUS_MESSAGE = "Payment with status: "
        private const val PAYMENT_ERROR_MESSAGE = "Error: "
        private const val PAYMENT_CANCEL_MESSAGE = "Cancel: "
        private const val REQUESTED_CODE_MESSAGE = "Requested code: "
        private const val RESULT_CODE_MESSAGE = " Result code: "
    }
}