package ar.com.wolox.android.cookbook.room.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * DAO defines the method that access the database, using annotation to bind SQL to each method.
 */
@Dao
interface RoomDataDao {

    @Query("Select id from personal_notes order by id desc limit 1")
    fun getLastIndex(): Int

    @Query("Select * from personal_notes")
    fun getAll(): List<RoomDataEntity>

    @Query("Select * from personal_notes where user like :user")
    fun findByUser(user: String): RoomDataEntity

    @Insert
    fun insertAll(vararg data: RoomDataEntity)

    @Delete
    fun deleteEntity(dataEntity: RoomDataEntity)

    @Update
    fun updateEntity(vararg dataEntity: RoomDataEntity)
}