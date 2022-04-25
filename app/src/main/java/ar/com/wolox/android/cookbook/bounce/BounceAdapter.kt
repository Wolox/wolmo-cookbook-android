package ar.com.wolox.android.cookbook.bounce

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.bounce.BounceAdapter.BounceViewHolder
import java.util.Random

class BounceAdapter(val context: Context) : RecyclerView.Adapter<BounceViewHolder>() {
    private var items = 1..5

    class BounceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var row: LinearLayout = view.findViewById(R.id.row)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BounceViewHolder {
        return BounceViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_bounce, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: BounceViewHolder, position: Int) {
        items.elementAt(position).also {
            viewHolder.apply {
                val rnd = Random()
                val color =
                    Color.argb(ALPHA, rnd.nextInt(BOUND), rnd.nextInt(BOUND), rnd.nextInt(BOUND))
                row.backgroundTintList = ColorStateList.valueOf(color)
            }
        }
    }

    override fun getItemCount() = items.count()

    companion object {
        const val ALPHA = 255
        const val BOUND = 256
    }
}
