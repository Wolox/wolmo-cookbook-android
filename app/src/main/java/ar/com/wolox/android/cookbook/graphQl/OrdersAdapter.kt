package ar.com.wolox.android.cookbook.graphQl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.model.ModelOrder
import kotlinx.android.synthetic.main.viewholder_order.view.*

class OrdersAdapter(private val context: Context) : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {
    private var orders: List<ModelOrder> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val viewHolder = LayoutInflater.from(context).inflate(R.layout.viewholder_order, parent, false)
        return OrderViewHolder(viewHolder)
    }

    fun setOrders(orders: List<ModelOrder>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = orders.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        orders[position].let {
            with(holder) {
                orderId.text = context.getString(R.string.order_id, it.id)
                username.text = context.getString(R.string.order_username, it.user?.username)
                orderTotal.text = context.getString(R.string.order_total, it.totalAmount.toString())
                deliveryAddress.text = context.getString(R.string.order_delivery_address, it.deliveryAddress)
            }
        }
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val orderId: TextView = itemView.vOrderId
        val deliveryAddress: TextView = itemView.vDeliveryAddress
        val orderTotal: TextView = itemView.vOrderTotal
        val username: TextView = itemView.vUsername
    }
}