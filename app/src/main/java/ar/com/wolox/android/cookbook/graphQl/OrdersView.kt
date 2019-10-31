package ar.com.wolox.android.cookbook.graphQl

import ar.com.wolox.android.cookbook.model.ModelOrder

interface OrdersView {

    fun onOrdersLoaded(orders: List<ModelOrder>)

    fun onError()

    fun toggleProgress(toggle: Boolean)
}