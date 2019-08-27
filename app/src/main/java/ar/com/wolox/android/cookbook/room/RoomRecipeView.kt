package ar.com.wolox.android.cookbook.room

import ar.com.wolox.android.cookbook.room.database.RoomDataEntity

interface RoomRecipeView {

    fun loginSuccess()
    fun loginError()
    fun logout()

    fun updateEntities(entities: List<RoomDataEntity>)
    fun insertEntity(entity: RoomDataEntity)
    fun clearEntities()
    fun deleteEntity(entity: RoomDataEntity)
    fun modifyEntity(entity: RoomDataEntity)
}