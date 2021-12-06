package ar.com.wolox.android.cookbook.graphQl

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.FragmentOrdersBinding
import ar.com.wolox.android.cookbook.model.ModelOrder
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_orders.*

class OrdersFragment : WolmoFragment<FragmentOrdersBinding, OrdersPresenter>(), OrdersView {

    private lateinit var ordersAdapter: OrdersAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun layout(): Int = R.layout.fragment_orders

    override fun init() {
        setUpRecyclerView()
        setOnClickListeners()
    }

    override fun onOrdersLoaded(orders: List<ModelOrder>) {
        requireActivity().runOnUiThread {
            toggleProgress(false)
            ordersAdapter.setOrders(orders)
        }
    }

    override fun onError() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    private fun setUpRecyclerView() {
        ordersAdapter = OrdersAdapter(requireContext())
        layoutManager = LinearLayoutManager(requireContext())
        binding!!.vRecyclerView.apply {
            adapter = ordersAdapter
            layoutManager = layoutManager
            isNestedScrollingEnabled = false
        }
    }

    private fun setOnClickListeners() {
        binding!!.vMakeGraphQLRequest.setOnClickListener {
            toggleProgress(true)
            presenter.getOrders(1, 10)
        }
    }

    override fun toggleProgress(toggle: Boolean) {
        binding!!.vProgressBar.visibility = if (toggle) View.VISIBLE else View.GONE
    }

    companion object {
        const val PAGE_ONE = 1
        const val PAGE_LIMIT = 10
    }
}
