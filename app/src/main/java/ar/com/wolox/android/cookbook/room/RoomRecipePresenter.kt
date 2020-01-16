package ar.com.wolox.android.cookbook.room

import ar.com.wolox.android.cookbook.room.database.models.NoteEntity
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter

abstract class RoomRecipePresenter : CoroutineBasePresenter<RoomRecipeView>() {

    abstract fun onSessionButtonClicked(user: String)

    abstract fun onAddButtonClicked()

    abstract fun onPositiveAddButtonClicked(newData: String)

    abstract fun onClearButtonClicked()

    abstract fun onEditButtonClicked(item: NoteEntity)

    abstract fun onPositiveEditButtonClicked(entity: NoteEntity, newData: String)

    abstract fun onDeleteButtonClicked(entity: NoteEntity)
}