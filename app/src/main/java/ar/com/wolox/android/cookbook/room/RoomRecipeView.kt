package ar.com.wolox.android.cookbook.room

import ar.com.wolox.android.cookbook.room.database.models.NoteEntity

interface RoomRecipeView {

    fun showLoginSuccess()

    fun showLoginError()

    fun doSessionLogout()

    fun showAddInputDialog()

    fun showEditInputDialog(entity: NoteEntity)

    fun updateEntities(entities: List<NoteEntity>)

    fun insertEntity(entity: NoteEntity)

    fun clearEntities()

    fun deleteEntity(entity: NoteEntity)

    fun modifyEntity(entity: NoteEntity)
}