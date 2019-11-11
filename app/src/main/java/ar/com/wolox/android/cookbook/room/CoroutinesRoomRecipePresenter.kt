package ar.com.wolox.android.cookbook.room

import ar.com.wolox.android.cookbook.room.database.models.NoteEntity
import ar.com.wolox.android.cookbook.room.database.services.interfaces.NoteService
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutinesRoomRecipePresenter @Inject constructor(
        private val noteService: NoteService
) : RoomRecipePresenter() {

    private var userName: String? = null

    override fun onSessionButtonClicked(user: String) {
        userName?.let {
            userName = null
            view.doSessionLogout()
        } ?: run {
            if (user.isNotEmpty()) {
                userName = user

                launch {
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

    override fun onAddButtonClicked() {
        view.showAddInputDialog()
    }

    override fun onPositiveAddButtonClicked(newData: String) {
        launch {
            val lastIndex = noteService.getLastIndex()

            val entity = NoteEntity(lastIndex + 1, userName!!, newData)
            noteService.save(entity)
            view.insertEntity(entity)
        }
    }

    override fun onClearButtonClicked() {
        launch {
            noteService.deleteAll()
            view.clearEntities()
        }
    }

    override fun onEditButtonClicked(item: NoteEntity) {
        view.showEditInputDialog(item)
    }

    override fun onPositiveEditButtonClicked(entity: NoteEntity, newData: String) {
        launch {
            val newEntity = entity.copy(user = userName!!, data = newData)
            noteService.save(newEntity)
            view.modifyEntity(newEntity)
        }
    }

    override fun onDeleteButtonClicked(entity: NoteEntity) {
        launch {
            noteService.delete(entity)
            view.deleteEntity(entity)
        }
    }
}