package ar.com.wolox.android.cookbook.graphQl

import android.util.Log
import ar.com.wolox.android.cookbook.model.ModelOrder
import ar.com.wolox.android.cookbook.netowork.GraphQlService
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.wolox.queries.GetOrdersQuery
import javax.inject.Inject

class OrdersPresenter @Inject constructor() : BasePresenter<OrdersView>() {
    private var orderList: ArrayList<ModelOrder>? = null

    fun init() {
        orderList = ArrayList()
    }

    fun getOrders(page: Int, limit: Int) {
        GraphQlService.getOrders(page, limit).enqueue(object : ApolloCall.Callback<GetOrdersQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                Log.v("ERROR---", e.toString())
            }

            override fun onResponse(response: Response<GetOrdersQuery.Data>) {
                response.data()?.orders()?.orders()?.forEach {
                    Log.v("ok---", it.id())
                    this@OrdersPresenter.orderList?.add(ModelOrder.fromGraphQl(it))
                    view.onOrdersLoaded(orderList!!)
                }
            }
        })
    }
}