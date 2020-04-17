package ar.com.wolox.android.cookbook.mercadopago

import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.widget.Toast
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.model.Payment
import com.mercadopago.android.px.model.exceptions.MercadoPagoError
import kotlinx.android.synthetic.main.fragment_mercadopago.*
import javax.inject.Inject

class MercadoPagoRecipeFragment @Inject constructor() : WolmoFragment<MercadoPagoPresenter>(), MercadoPagoView {

    override fun layout(): Int = R.layout.fragment_mercadopago

    override fun init() {
        payButton.setOnClickListener { presenter.onPayButtonClicked() }
    }

    override fun payProduct(checkout: MercadoPagoCheckout) {
        checkout.startPayment(requireContext(), REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {

            if (resultCode == MercadoPagoCheckout.PAYMENT_RESULT_CODE) {
                val payment = data!!.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT) as Payment

                Toast.makeText(context, StringBuilder()
                        .append(PAYMENT_WITH_STATUS_MESSAGE)
                        .append(payment), Toast.LENGTH_LONG)
                        .show()
            } else if (resultCode == RESULT_CANCELED) {

                if (data != null && data.extras != null && data.extras!!.containsKey(MercadoPagoCheckout.EXTRA_ERROR)) {
                    val mercadoPagoError = data.getSerializableExtra(MercadoPagoCheckout.EXTRA_ERROR) as MercadoPagoError
                    Toast.makeText(context, "Error: $mercadoPagoError", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, StringBuilder()
                            .append("Cancel - ")
                            .append(REQUESTED_CODE_MESSAGE)
                            .append(requestCode)
                            .append(RESULT_CODE_MESSAGE)
                            .append(resultCode), Toast.LENGTH_LONG)
                            .show()
                }
            } else {
                Toast.makeText(context, StringBuilder()
                        .append(REQUESTED_CODE_MESSAGE)
                        .append(requestCode)
                        .append(RESULT_CODE_MESSAGE)
                        .append(resultCode), Toast.LENGTH_LONG)
                        .show()
            }
        }
    }

    companion object {

        private const val REQUEST_CODE = 1
        private const val PAYMENT_WITH_STATUS_MESSAGE = "Payment with status: "
        private const val REQUESTED_CODE_MESSAGE = "Requested code: "
        private const val RESULT_CODE_MESSAGE = " Result code: "

        fun newInstance() = MercadoPagoRecipeFragment()
    }
}