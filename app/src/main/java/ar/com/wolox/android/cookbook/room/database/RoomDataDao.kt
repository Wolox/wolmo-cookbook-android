package ar.com.wolox.android.cookbook.room.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoomDataDao {

    @Query("Select * from personal_notes")
    fun getAll(): List<RoomDataEntity>

    @Query("Select * from personal_notes where user like :user")
    fun findByUser(user: String): RoomDataEntity

    @Insert
    fun insertAll(vararg data: RoomDataEntity)

    @Delete
    fun delete(dataEntity: RoomDataEntity)

    @Update
    fun updateTodo(vararg dataEntity: RoomDataEntity)
}