package ar.com.wolox.android.cookbook.mercadopago

import android.content.Context
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.mercadopago.android.px.core.MercadoPagoCheckout
import javax.inject.Inject

class MercadoPagoPresenter @Inject constructor(val context: Context) : BasePresenter<MercadoPagoView>() {

    fun onPayButtonClicked() {
        view?.payProduct(MercadoPagoCheckout.Builder(
                context.getString(PUBLIC_KEY),
                DUMMY_PREFERENCE_ID)
                .build())
    }

    companion object {
        private const val PUBLIC_KEY = R.string.mercado_pago_public_key

        private const val DUMMY_PREFERENCE_ID = "243962506-0bb62e22-5c7b-425e-a0a6-c22d0f4758a9"
    }
}
