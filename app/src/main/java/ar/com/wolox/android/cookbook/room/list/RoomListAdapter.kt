package ar.com.wolox.android.cookbook.room.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.room.database.RoomDataEntity
import kotlinx.android.synthetic.main.item_db_row.view.*

class RoomListAdapter(
        private val dataSet: MutableList<RoomDataEntity>,
        private val editClickListener: (RoomDataEntity) -> Unit,
        private val deleteClickListener: (RoomDataEntity) -> Unit
) : RecyclerView.Adapter<RoomListAdapter.EntityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        return EntityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_db_row, parent, false))
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val dataEntity: RoomDataEntity = dataSet[position]
        holder.bind(dataEntity, editClickListener, deleteClickListener)
    }

    override fun getItemCount(): Int = dataSet.size

    fun addData(dataEntity: RoomDataEntity) {
        this.dataSet.add(dataEntity)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.dataSet.clear()
        notifyDataSetChanged()
    }

    fun deleteData(dataEntity: RoomDataEntity) {
        this.dataSet.remove(dataEntity)
        notifyDataSetChanged()
    }

    fun editData(dataEntity: RoomDataEntity) {
        this.dataSet[dataEntity.id - 1] = dataEntity
        notifyDataSetChanged()
    }

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
                entity: RoomDataEntity,
                editClickListener: (RoomDataEntity) -> Unit,
                deleteClickListener: (RoomDataEntity) -> Unit
        ) {

            val dataStr = itemView.context.getString(R.string.room_row_data, entity.user, entity.data)
            itemView.apply {

                vId.text = entity.id.toString()
                vData.text = dataStr
                vEditBtn.setOnClickListener { editClickListener(entity) }
                vDeleteBtn.setOnClickListener { deleteClickListener(entity) }
            }
        }
    }
}
