package ar.com.wolox.android.cookbook.mercadopago

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentMercadopagoBinding
import ar.com.wolox.android.cookbook.mercadopago.handler.MercadoPagoResultHandler
import ar.com.wolox.android.cookbook.mercadopago.handler.MercadoPagoResultListener
import ar.com.wolox.android.cookbook.mercadopago.model.Product
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.model.Payment
import com.mercadopago.android.px.model.exceptions.MercadoPagoError
import javax.inject.Inject

class MercadoPagoRecipeFragment : WolmoFragment<MercadoPagoPresenter>(), MercadoPagoView {

    @Inject
    lateinit var handler: MercadoPagoResultHandler

    @Inject
    lateinit var toastFactory: ToastFactory

    private var _binding: FragmentMercadopagoBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView.

    private val itemList = mutableListOf<Pair<Product, Int>>()
    private val adapter = MercadoPagoItemsAdapter(itemList)

    // This is no longer necessary since we're using view binding, but if I remove it then super.onCreateView crash.
    override fun layout() = R.layout.fragment_mercadopago

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentMercadopagoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.payButton.setOnClickListener {
            presenter.onPayButtonClicked(
                binding.clientNameInput.text.toString(),
                binding.clientEmailInput.text.toString())
        }
        binding.itemList.adapter = adapter
    }

    override fun showProducts(items: List<Pair<Product, Int>>) {
        itemList.clear()
        itemList.addAll(items)
        adapter.notifyDataSetChanged()
    }

    override fun showTotal(total: Float) {
        binding.itemsTotalText.text = getString(R.string.mercadopago_total_number, total)
    }

    override fun showEmptyNameError() {
        binding.clientNameInput.error = getString(R.string.mercadopago_empty_name_error)
    }

    override fun showEmptyEmailError() {
        binding.clientNameInput.error = getString(R.string.mercadopago_empty_email_error)
    }

    override fun showInvalidEmailError() {
        binding.clientNameInput.error = getString(R.string.mercadopago_invalid_email_error)
    }

    override fun showUnexpectedError() {
        toastFactory.show(R.string.unexpected_error)
    }

    override fun startLoading() {
        binding.loading.isVisible = true
        binding.payButton.isEnabled = false
    }

    override fun finishLoading() {
        binding.loading.isVisible = false
        binding.payButton.isEnabled = true
    }

    override fun payProduct(checkout: MercadoPagoCheckout) {
        checkout.startPayment(requireContext(), MercadoPagoResultHandler.REQUEST_CODE)
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
        toastFactory.show(getString(R.string.mercadopago_payment_message, message))
    }

    override fun showErrorMessage(errorMessage: String) {
        toastFactory.show(getString(R.string.mercadopago_error_message, errorMessage))
    }

    override fun showCanceledMessage() {
        toastFactory.show(R.string.mercadopago_cancel_message)
    }

    companion object {

        fun newInstance() = MercadoPagoRecipeFragment()
    }
}