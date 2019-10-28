package ar.com.wolox.android.cookbook.room.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.room.database.models.NoteEntity
import kotlinx.android.synthetic.main.item_db_row.view.*

class RoomListAdapter(
        private val dataSet: MutableList<NoteEntity>,
        private val editClickListener: (NoteEntity) -> Unit,
        private val deleteClickListener: (NoteEntity) -> Unit
) : RecyclerView.Adapter<RoomListAdapter.EntityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        return EntityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_db_row, parent, false))
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val dataEntity: NoteEntity = dataSet[position]
        holder.bind(dataEntity, editClickListener, deleteClickListener)
    }

    override fun getItemCount(): Int = dataSet.size

    fun addData(dataEntity: NoteEntity) {
        this.dataSet.add(dataEntity)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.dataSet.clear()
        notifyDataSetChanged()
    }

    fun deleteData(dataEntity: NoteEntity) {
        this.dataSet.remove(dataEntity)
        notifyDataSetChanged()
    }

    fun editData(dataEntity: NoteEntity) {
        val position = dataSet.indexOfFirst { it.id == dataEntity.id }
        this.dataSet.removeAt(position)
        this.dataSet.add(position, dataEntity)
        notifyDataSetChanged()
    }

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
                entity: NoteEntity,
                editClickListener: (NoteEntity) -> Unit,
                deleteClickListener: (NoteEntity) -> Unit
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
