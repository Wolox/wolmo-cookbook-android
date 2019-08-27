package ar.com.wolox.android.cookbook.room

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.room.Room
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.android.cookbook.room.database.RoomDataEntity
import ar.com.wolox.android.cookbook.room.database.RoomDatabaseManager
import ar.com.wolox.android.cookbook.room.dialog.RoomInputDialog
import ar.com.wolox.android.cookbook.room.dialog.RoomInputDialogListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_room.*
import java.util.Calendar

class RoomRecipeFragment : WolmoFragment<RoomRecipePresenter>(), RoomRecipeView {

    private lateinit var db: RoomDatabaseManager

    override fun layout(): Int = R.layout.fragment_room

    override fun init() {

        vSessionBtn.visibility = View.VISIBLE
        vSessionBtn.text = getString(R.string.room_login)

        vRecyclerView.visibility = View.INVISIBLE

        vAddBtn.visibility = View.INVISIBLE
        vClearBtn.visibility = View.INVISIBLE

        db = Room.databaseBuilder(
                context!!,
                RoomDatabaseManager::class.java, "wx_database.db"
        ).build()
    }

    override fun setListeners() {
        super.setListeners()

        vSessionBtn.setOnClickListener {
            presenter.onSessionButtonClicked(vUser.text.toString())
        }

        vAddBtn.setOnClickListener {
            RoomInputDialog().showDialog(context!!, object : RoomInputDialogListener {
                override fun onPositiveButtonClicked(data: String) {
                    // presenter.onAddButtonClicked(data)

                    Log.e("FedeLog", "InserQuery")
                    Single.fromCallable {
                        val entity = RoomDataEntity()
                        entity.id = Calendar.getInstance().timeInMillis.toInt()
                        entity.user = "Fede"
                        entity.data = data
                        db.RoomDataDao().insertAll(entity)
                    }.subscribeOn(Schedulers.io())
                }

                override fun onNegativeButtonClicked() {
                }
            }).show()
        }

        vClearBtn.setOnClickListener {
            // presenter.onClearButtonClicked()

            val result = db.RoomDataDao().getAll()
            Log.e("FedeLog", "DB query")
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