package ar.com.wolox.android.cookbook.mercadopago

import android.content.Intent
import android.widget.Toast
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.mercadopago.handler.MercadoPagoResultHandler
import ar.com.wolox.android.cookbook.mercadopago.handler.MercadoPagoResultListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.model.Payment
import com.mercadopago.android.px.model.exceptions.MercadoPagoError
import kotlinx.android.synthetic.main.fragment_mercadopago.*
import javax.inject.Inject

class MercadoPagoRecipeFragment @Inject constructor() : WolmoFragment<MercadoPagoPresenter>(), MercadoPagoView {

    @Inject
    lateinit var handler: MercadoPagoResultHandler

    override fun layout(): Int = R.layout.fragment_mercadopago

    override fun init() {
        payButton.setOnClickListener { presenter.onPayButtonClicked() }
    }

    override fun payProduct(checkout: MercadoPagoCheckout) {
        checkout.startPayment(requireContext(), REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        handler.onActivityResult(requestCode, resultCode, data, object : MercadoPagoResultListener {
            override fun onSuccess(paymentResult: Payment) {
                presenter.onPaymentSuccess(paymentResult)
            }

            override fun onMercadoPagoError(mercadoPagoError: MercadoPagoError) {
                presenter.onMercadoPagoError(mercadoPagoError)
            }

            override fun onError(errorMessage: String) {
                presenter.onError(errorMessage)
            }

            override fun onCanceled() {
                presenter.onCanceled()
            }
        })
    }

    override fun proceedResult(message: String) {
        Toast.makeText(context,
                getString(R.string.mercadopago_payment_message, message),
                Toast.LENGTH_LONG
        ).show()
    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(context,
                getString(R.string.mercadopago_error_message, errorMessage),
                Toast.LENGTH_LONG
        ).show()
    }

    override fun showCanceledMessage() {
        Toast.makeText(context, R.string.mercadopago_cancel_message, Toast.LENGTH_LONG).show()
    }

    companion object {

        private const val REQUEST_CODE = 1

        fun newInstance() = MercadoPagoRecipeFragment()
    }
}