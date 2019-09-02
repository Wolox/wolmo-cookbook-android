package ar.com.wolox.android.cookbook.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomDataEntity::class], version = 1, exportSchema = false)
abstract class RoomDatabaseManager : RoomDatabase() {

    abstract fun RoomDataDao(): RoomDataDao

    /**
     * "invoke" function returns database instance if exists, if not it will return a new instance
     */
    companion object {
        @Volatile
        private var instance: RoomDatabaseManager? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
                RoomDatabaseManager::class.java, "wx_database.db")
                .build()
    }
}