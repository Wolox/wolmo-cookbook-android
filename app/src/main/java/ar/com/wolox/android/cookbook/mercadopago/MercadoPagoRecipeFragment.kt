package ar.com.wolox.android.cookbook.mercadopago

import android.content.Intent
import android.widget.Toast
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.mercadopago.android.px.core.MercadoPagoCheckout
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
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun proceedResult(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    companion object {

        private const val REQUEST_CODE = 1

        fun newInstance() = MercadoPagoRecipeFragment()
    }
}