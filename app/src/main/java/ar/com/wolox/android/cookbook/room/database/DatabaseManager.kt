package ar.com.wolox.android.cookbook.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ar.com.wolox.android.cookbook.room.database.models.NoteEntity
import ar.com.wolox.android.cookbook.room.database.persitence.NoteDao

/**
 * Database is a holder class that uses annotation to define the list of entities and database version.
 * This class content defines the list of DAOs.
 */
@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {

    /**
     * Define DAO (DataAccessObject) interfaces with sql queries
     */
    abstract fun RoomDataDao(): NoteDao

    /**
     * "invoke" function returns database instance if exists, if not it will return a new instance
     */
    companion object {
        private const val DB_NAME = "wx_database.db"

        @Volatile
        private var instance: DatabaseManager? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
                DatabaseManager::class.java, DB_NAME)
                .build()
    }
}