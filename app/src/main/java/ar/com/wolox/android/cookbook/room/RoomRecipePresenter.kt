package ar.com.wolox.android.cookbook.room

import android.app.Application
import android.os.Handler
import ar.com.wolox.android.cookbook.room.database.RoomDataEntity
import ar.com.wolox.android.cookbook.room.database.RoomDatabaseManager
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class RoomRecipePresenter @Inject constructor(
    val application: Application
) : BasePresenter<RoomRecipeView>() {

    private lateinit var db: RoomDatabaseManager
    private var userName: String? = null
    private val handler = Handler()

    override fun onViewAttached() {
        super.onViewAttached()
        db = RoomDatabaseManager.invoke(application.baseContext)
    }

    fun onSessionButtonClicked(user: String) {
        userName?.let {
            userName = null
            view.doSessionLogout()
        } ?: run {
            if (user.isNotEmpty()) {
                userName = user
                Thread(Runnable {
                    val data = db.RoomDataDao().getAll()
                    handler.post {
                        view.updateEntities(data)
                        view.showLoginSuccess()
                    }
                }).start()
            } else {
                view.showLoginError()
            }
        }
    }

    fun onAddButtonClicked() {
        view.showAddInputDialog()
    }

    fun onPositiveAddButtonClicked(newData: String) {
        Thread(Runnable {
            var index = db.RoomDataDao().getLastIndex()
            index = if (index <= 0) 1 else index + 1

            val entity = RoomDataEntity(index, userName!!, newData)

            db.RoomDataDao().insertAll(entity)
            handler.post { view.insertEntity(entity) }
        }).start()
    }

    fun onClearButtonClicked() {
        Thread(Runnable {
            db.clearAllTables()
            handler.post { view.clearEntities() }
        }).start()
    }

    fun onEditButtonClicked(item: RoomDataEntity) {
        view.showEditInputDialog(item)
    }

    fun onPositiveEditButtonClicked(entity: RoomDataEntity, newData: String) {
        Thread(Runnable {
            entity.run {
                user = userName!!
                data = newData
            }

            db.RoomDataDao().updateEntity(entity)
            handler.post { view.modifyEntity(entity) }
        }).start()
    }

    fun onDeleteButtonClicked(entity: RoomDataEntity) {
        Thread(Runnable {
            db.RoomDataDao().deleteEntity(entity)
            handler.post { view.deleteEntity(entity) }
        }).start()
    }
}