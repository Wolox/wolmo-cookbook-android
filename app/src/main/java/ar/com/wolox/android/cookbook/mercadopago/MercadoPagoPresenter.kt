package ar.com.wolox.android.cookbook.mercadopago

import ar.com.wolox.android.cookbook.BuildConfig
import ar.com.wolox.android.cookbook.common.di.CoroutineDispatchersModule
import ar.com.wolox.android.cookbook.common.utils.isValidEmail
import ar.com.wolox.android.cookbook.mercadopago.model.Cart
import ar.com.wolox.android.cookbook.mercadopago.model.Client
import ar.com.wolox.android.cookbook.mercadopago.model.Product
import ar.com.wolox.android.cookbook.mercadopago.network.MercadoPagoAdapter
import ar.com.wolox.android.cookbook.mercadopago.network.MercadoPagoProductsRepository
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.model.Payment
import com.mercadopago.android.px.model.exceptions.MercadoPagoError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class MercadoPagoPresenter @Inject constructor(
    @Named(CoroutineDispatchersModule.MAIN) mainDispatcher: CoroutineDispatcher,
    private val mercadoPagoProductsRepository: MercadoPagoProductsRepository,
    private val mercadoPagoAdapter: MercadoPagoAdapter
) : CoroutineBasePresenter<MercadoPagoView>(mainDispatcher) {

    private var items: List<Pair<Product, Int>>? = null

    override fun onViewAttached() {
        view?.startLoading()
        launch {
            // There's no reason to make all the cart flow,
            // so mocking the cart by randomly assign a quantity to each product seems good.
            items = mercadoPagoProductsRepository.getProducts().map { it to (1..5).random() }.also {
                view?.showProducts(it)
                view?.showTotal(it.fold(0f) { accumulator, item -> accumulator + item.second * item.first.price })
            }
            view?.finishLoading()
        }
    }

    fun onPaymentSuccess(paymentMessage: Payment) {
        view?.showPaymentSuccessResult(paymentMessage)
    }

    fun onMercadoPagoError(mercadoPagoError: MercadoPagoError) {
        view?.showErrorMessage(mercadoPagoError.errorDetail)
    }

    fun onError(errorMessage: String) {
        view?.showErrorMessage(errorMessage)
    }

    fun onCanceled() {
        view?.showCanceledMessage()
    }

    fun onPayButtonClicked(clientName: String, clientEmail: String) {
        view?.startLoading()

        if (!validateClient(clientName, clientEmail)) {
            return finishLoading()
        }

        val client = Client(clientName, clientEmail)
        val items = items ?: return finishLoading()
        val cart = Cart(client, items.map { it.first.id to it.second })

        launch {
            mercadoPagoAdapter.checkout(cart)?.let {
                view?.payProduct(MercadoPagoCheckout.Builder(BuildConfig.MERCADO_PAGO_PUBLIC_KEY, it.id).build())
            } ?: run {
                view?.showUnexpectedError()
            }
            finishLoading()
        }
    }

    private fun finishLoading() {
        view?.finishLoading()
    }

    private fun validateClient(clientName: String, clientEmail: String): Boolean {
        var isValid = true

        if (clientName.isBlank()) {
            view?.showEmptyNameError()
            isValid = false
        }

        if (clientEmail.isBlank()) {
            view?.showEmptyEmailError()
            isValid = false
        } else if (!clientEmail.isValidEmail) {
            view?.showInvalidEmailError()
            isValid = false
        }

        return isValid
    }
}