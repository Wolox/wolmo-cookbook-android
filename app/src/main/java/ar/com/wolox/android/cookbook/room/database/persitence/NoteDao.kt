package ar.com.wolox.android.cookbook.room.database.persitence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.com.wolox.android.cookbook.room.database.models.NoteEntity

/**
 * DAO defines the method that access the database, using annotation to bind SQL to each method.
 */
@Dao
interface NoteDao {

    @Query("Select id from NoteEntity order by id desc limit 1")
    fun getLastIndex(): Int

    @Query("Select * from NoteEntity")
    fun getAll(): List<NoteEntity>

    @Query("Select * from NoteEntity where user like :user")
    fun findByUser(user: String): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(data: NoteEntity)

    @Delete
    fun delete(dataEntity: NoteEntity)

    @Query("Delete from NoteEntity")
    fun deleteAll()
}