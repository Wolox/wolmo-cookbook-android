package ar.com.wolox.android.cookbook.room

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.room.database.models.NoteEntity
import ar.com.wolox.android.cookbook.room.dialog.RoomInputDialog
import ar.com.wolox.android.cookbook.room.dialog.RoomInputDialogListener
import ar.com.wolox.android.cookbook.room.list.RoomListAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_room.*
import javax.inject.Inject

class RoomRecipeFragment @Inject constructor(
    val toastFactory: ToastFactory
) : WolmoFragment<CoroutinesRoomRecipePresenter>(), RoomRecipeView {

    private lateinit var viewAdapter: RoomListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var entityItemList: MutableList<NoteEntity>

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

    override fun showAddInputDialog() {
        RoomInputDialog().showDialog(requireContext(), R.string.room_input_title_add, object : RoomInputDialogListener {
            override fun onPositiveButtonClicked(data: String) {
                presenter.onPositiveAddButtonClicked(data)
            }

            override fun onNegativeButtonClicked() {
            }
        }).show()
    }

    override fun updateEntities(entities: List<NoteEntity>) {

        entityItemList = mutableListOf()
        entityItemList.addAll(entities)

        viewAdapter = RoomListAdapter(entityItemList, { item ->
            presenter.onEditButtonClicked(item)
        }, { item ->
            presenter.onDeleteButtonClicked(item)
        })
        vRecyclerView.run {
            visibility = View.VISIBLE
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        viewAdapter.notifyDataSetChanged()
    }

    override fun showEditInputDialog(entity: NoteEntity) {
        RoomInputDialog().showDialog(requireContext(), R.string.room_input_title_modify, object : RoomInputDialogListener {
            override fun onPositiveButtonClicked(data: String) {
                presenter.onPositiveEditButtonClicked(entity, data)
            }

            override fun onNegativeButtonClicked() {
            }
        }).show()
    }

    override fun showLoginSuccess() {
        vSessionBtn.text = getString(R.string.room_logout)
        vUser.isEnabled = false
        vRecyclerView.visibility = View.VISIBLE
        vAddBtn.visibility = View.VISIBLE
        vClearBtn.visibility = View.VISIBLE
    }

    override fun showLoginError() {
        toastFactory.show(R.string.room_login_error)
    }

    override fun doSessionLogout() {
        vSessionBtn.text = getString(R.string.room_login)
        vUser.run {
            isEnabled = true
            setText("")
        }
        vRecyclerView.visibility = View.INVISIBLE
        vAddBtn.visibility = View.INVISIBLE
        vClearBtn.visibility = View.INVISIBLE
        toastFactory.show(R.string.room_logout)
    }

    override fun insertEntity(entity: NoteEntity) {
        viewAdapter.addData(entity)
        toastFactory.show(R.string.room_row_inserted)
    }

    override fun clearEntities() {
        viewAdapter.clearData()
        toastFactory.show(R.string.room_rows_deleted)
    }

    override fun deleteEntity(entity: NoteEntity) {
        viewAdapter.deleteData(entity)
        toastFactory.show(R.string.room_row_deleted)
    }

    override fun modifyEntity(entity: NoteEntity) {
        viewAdapter.editData(entity)
        toastFactory.show(R.string.room_row_modified)
    }
}