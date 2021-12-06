package ar.com.wolox.android.cookbook.recipepicker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ar.com.wolox.android.cookbook.common.utils.BindingViewHolder
import ar.com.wolox.android.cookbook.databinding.ItemRecipeBinding

class RecipePickerAdapter :
    ListAdapter<RecipeItem, BindingViewHolder<RecipeItem, ItemRecipeBinding>>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<RecipeItem, ItemRecipeBinding> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(layoutInflater)
        return RecipeViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(
        holder: BindingViewHolder<RecipeItem, ItemRecipeBinding>,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class RecipeViewHolder(
        binding: ItemRecipeBinding,
        private val context: Context
    ) : BindingViewHolder<RecipeItem, ItemRecipeBinding>(binding) {

        override fun bind(item: RecipeItem) {
            with(binding) {
                recipeIconContainer.setCardBackgroundColor(ContextCompat.getColor(context, item.backgroundResId))
                vRecipeIcon.setImageResource(item.imageResId)
                vRecipeIcon.clipToOutline = true
                vRecipeTitle.text = context.getString(item.stringResId)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RecipeItem>() {
            override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean =
                oldItem.recipe == newItem.recipe

            override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean =
                oldItem == newItem
        }
    }
}
