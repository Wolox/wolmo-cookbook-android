package ar.com.wolox.android.cookbook.instagramlogin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.instagramlogin.model.InstagramDataItem
import kotlinx.android.synthetic.main.item_instagram_data.view.*

class InstagramLoginRecipeAdapter(
        private val dataSet: MutableList<InstagramDataItem>
) : RecyclerView.Adapter<InstagramLoginRecipeAdapter.InstagramLoginRecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstagramLoginRecipeViewHolder {
        return InstagramLoginRecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_instagram_data, parent, false))
    }

    override fun onBindViewHolder(holder: InstagramLoginRecipeViewHolder, position: Int) {
        val picture: InstagramDataItem = dataSet[position]
        holder.bind(picture)
    }

    override fun getItemCount(): Int = dataSet.size

    /** Note: Full info sample in "assets > InstagramResponseExample.json" **/
    class InstagramLoginRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
                picture: InstagramDataItem
        ) {

            itemView.vPicture.setImageURI(picture.images.standardImg.url)

            val username = itemView.context.getString(R.string.instagram_login_data,
                    picture.caption.from.id,
                    picture.caption.from.fullName,
                    picture.caption.from.username)
            itemView.vUser.text = username

            itemView.vDescription.text = picture.caption.text
        }
    }
}