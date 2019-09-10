package ar.com.wolox.android.cookbook.room

import ar.com.wolox.android.cookbook.room.database.RoomDataEntity

interface RoomRecipeView {

    fun showLoginSuccess()

    fun showLoginError()

    fun doSessionLogout()

    fun showAddInputDialog()

    fun showEditInputDialog(entity: RoomDataEntity)

    fun updateEntities(entities: List<RoomDataEntity>)

    fun insertEntity(entity: RoomDataEntity)

    fun clearEntities()

    fun deleteEntity(entity: RoomDataEntity)

    fun modifyEntity(entity: RoomDataEntity)
}