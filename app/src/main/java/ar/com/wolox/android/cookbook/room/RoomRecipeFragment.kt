package ar.com.wolox.android.cookbook.room

import android.view.View
import android.widget.Toast
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.room.dialog.RoomInputDialog
import ar.com.wolox.android.cookbook.room.dialog.RoomInputDialogListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_room.*

class RoomRecipeFragment : WolmoFragment<RoomRecipePresenter>(), RoomRecipeView {

    override fun layout(): Int = R.layout.fragment_room

    override fun init() {

        vSessionBtn.visibility = View.VISIBLE
        vSessionBtn.text = getString(R.string.room_login)

        vRecyclerView.visibility = View.INVISIBLE

        vAddBtn.visibility = View.INVISIBLE
        vClearBtn.visibility = View.INVISIBLE
    }

    override fun setListeners() {
        super.setListeners()

        vSessionBtn.setOnClickListener {
            presenter.onSessionButtonClicked(vUser.text.toString())
        }

        vAddBtn.setOnClickListener {
            RoomInputDialog().showDialog(context!!, object : RoomInputDialogListener {
                override fun onPositiveButtonClicked(data: String) {
                    presenter.onAddButtonClicked(data)
                }

                override fun onNegativeButtonClicked() {
                }
            }).show()
        }

        vClearBtn.setOnClickListener {
            presenter.onClearButtonClicked()
        }
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
        vUser.isEnabled = true
        vUser.setText("")
        vRecyclerView.visibility = View.INVISIBLE
        vAddBtn.visibility = View.INVISIBLE
        vClearBtn.visibility = View.INVISIBLE
    }
}