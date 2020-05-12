package ar.com.wolox.android.cookbook.mercadopago

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.databinding.ViewMercadopagoItemBinding
import ar.com.wolox.android.cookbook.mercadopago.model.Product

class MercadoPagoItemsAdapter : ListAdapter<Pair<Product, Int>, MercadoPagoItemsAdapter.ViewHolder>(
    DiffItemCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ViewMercadopagoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(private val binding: ViewMercadopagoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pair<Product, Int>) {
            binding.itemTitle.text = item.first.name
            binding.itemTotal.text = binding.itemTitle.resources.getString(
                R.string.mercadopago_item_total, item.first.price, item.second)
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<Pair<Product, Int>>() {
        override fun areItemsTheSame(oldItem: Pair<Product, Int>, newItem: Pair<Product, Int>): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Pair<Product, Int>, newItem: Pair<Product, Int>): Boolean {
            return oldItem == newItem
        }
    }
}
