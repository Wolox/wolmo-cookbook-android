package ar.com.wolox.android.cookbook.room

import ar.com.wolox.android.cookbook.room.database.models.NoteEntity
import ar.com.wolox.android.cookbook.room.database.services.interfaces.NoteService
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomRecipePresenter @Inject constructor(
    private val noteService: NoteService
) : BasePresenter<RoomRecipeView>() {

    private var userName: String? = null

    fun onSessionButtonClicked(user: String) {
        userName?.let {
            userName = null
            view.doSessionLogout()
        } ?: run {
            if (user.isNotEmpty()) {
                userName = user

                GlobalScope.launch(Dispatchers.Main) {
                    val data = noteService.getAll()
                    with(view) {
                        updateEntities(data)
                        showLoginSuccess()
                    }
                }
            } else {
                view.showLoginError()
            }
        }
    }

    fun onAddButtonClicked() {
        view.showAddInputDialog()
    }

    fun onPositiveAddButtonClicked(newData: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val lastIndex = noteService.getLastIndex()

            val entity = NoteEntity(lastIndex + 1, userName!!, newData)
            noteService.save(entity)
            view.insertEntity(entity)
        }
    }

    fun onClearButtonClicked() {
        GlobalScope.launch(Dispatchers.Main) {
            noteService.deleteAll()
            view.clearEntities()
        }
    }

    fun onEditButtonClicked(item: NoteEntity) {
        view.showEditInputDialog(item)
    }

    fun onPositiveEditButtonClicked(entity: NoteEntity, newData: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val newEntity = entity.copy(user = userName!!, data = newData)
            noteService.save(newEntity)
            view.modifyEntity(newEntity)
        }
    }

    fun onDeleteButtonClicked(entity: NoteEntity) {
        GlobalScope.launch(Dispatchers.Main) {
            noteService.delete(entity)
            view.deleteEntity(entity)
        }
    }
}