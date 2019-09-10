package ar.com.wolox.android.cookbook.room

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.room.database.RoomDataEntity
import ar.com.wolox.android.cookbook.room.dialog.RoomInputDialog
import ar.com.wolox.android.cookbook.room.dialog.RoomInputDialogListener
import ar.com.wolox.android.cookbook.room.list.RoomListAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_room.*

class RoomRecipeFragment : WolmoFragment<RoomRecipePresenter>(), RoomRecipeView {

    private lateinit var viewAdapter: RoomListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var entityItemList: MutableList<RoomDataEntity>

    override fun layout(): Int = R.layout.fragment_room

    override fun init() {
        vSessionBtn.run {
            visibility = View.VISIBLE
            text = getString(R.string.room_login)
        }

        vRecyclerView.visibility = View.INVISIBLE

        vAddBtn.visibility = View.INVISIBLE
        vClearBtn.visibility = View.INVISIBLE

        viewManager = LinearLayoutManager(context)
    }

    override fun setListeners() {
        super.setListeners()

        vSessionBtn.setOnClickListener {
            presenter.onSessionButtonClicked(vUser.text.toString())
        }

        vAddBtn.setOnClickListener {
            presenter.onAddButtonClicked()
        }

        vClearBtn.setOnClickListener {
            presenter.onClearButtonClicked()
        }
    }

    override fun showInputDialog() {
        RoomInputDialog().showDialog(requireContext(), R.string.room_input_title_add, object : RoomInputDialogListener {
            override fun onPositiveButtonClicked(data: String) {
                presenter.onPositiveButtonClicked(data)
            }

            override fun onNegativeButtonClicked() {
            }
        }).show()
    }

    override fun updateEntities(entities: List<RoomDataEntity>) {

        entityItemList = mutableListOf()
        entityItemList.addAll(entities)

        viewAdapter = RoomListAdapter(entityItemList, { item -> editClickListener(item) }, { item -> deleteClickListener(item) })
        vRecyclerView.run {
            visibility = View.VISIBLE
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        viewAdapter.notifyDataSetChanged()
    }

    private fun editClickListener(item: RoomDataEntity) {
        RoomInputDialog().showDialog(requireContext(), R.string.room_input_title_modify, object : RoomInputDialogListener {
            override fun onPositiveButtonClicked(data: String) {
                presenter.onEditButtonClicked(item, data)
            }

            override fun onNegativeButtonClicked() {
            }
        }).show()
    }

    private fun deleteClickListener(item: RoomDataEntity) {
        presenter.onDeleteButtonClicked(item)
    }

    override fun loginSuccess() {
        vSessionBtn.text = getString(R.string.room_logout)
        vUser.isEnabled = false
        vRecyclerView.visibility = View.VISIBLE
        vAddBtn.visibility = View.VISIBLE
        vClearBtn.visibility = View.VISIBLE
    }

    override fun loginError() {
        Toast.makeText(context, getString(R.string.room_login_error), Toast.LENGTH_LONG).show()
    }

    override fun logout() {
        vSessionBtn.text = getString(R.string.room_login)
        vUser.run {
            isEnabled = true
            setText("")
        }
        vRecyclerView.visibility = View.INVISIBLE
        vAddBtn.visibility = View.INVISIBLE
        vClearBtn.visibility = View.INVISIBLE
        Toast.makeText(context, getString(R.string.room_logout), Toast.LENGTH_LONG).show()
    }

    override fun insertEntity(entity: RoomDataEntity) {
        viewAdapter.addData(entity)
        Toast.makeText(context, getString(R.string.room_row_inserted), Toast.LENGTH_SHORT).show()
    }

    override fun clearEntities() {
        viewAdapter.clearData()
        Toast.makeText(context, getString(R.string.room_rows_deleted), Toast.LENGTH_SHORT).show()
    }

    override fun deleteEntity(entity: RoomDataEntity) {
        viewAdapter.deleteData(entity)
        Toast.makeText(context, getString(R.string.room_row_deleted), Toast.LENGTH_SHORT).show()
    }

    override fun modifyEntity(entity: RoomDataEntity) {
        viewAdapter.editData(entity)
        Toast.makeText(context, getString(R.string.room_row_modified), Toast.LENGTH_SHORT).show()
    }
}