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
            view.logout()
        } ?: run {
            if (user.isNotEmpty()) {
                userName = user
                Thread(Runnable {
                    val data = db.RoomDataDao().getAll()
                    handler.post {
                        view.updateEntities(data)
                        view.loginSuccess()
                    }
                }).start()
            } else {
                view.loginError()
            }
        }
    }

    fun onAddButtonClicked(data: String) {

        Thread(Runnable {
            val entity = RoomDataEntity()
            var index = db.RoomDataDao().getLastIndex()
            if (index <= 0) {
                index = 1
            } else {
                index += 1
            }
            entity.id = index
            entity.user = userName!!
            entity.data = data

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
}