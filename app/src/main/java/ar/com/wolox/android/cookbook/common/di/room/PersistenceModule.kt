package ar.com.wolox.android.cookbook.common.di.room

import android.content.Context
import ar.com.wolox.android.cookbook.room.database.DatabaseManager
import ar.com.wolox.android.cookbook.room.database.persitence.NoteDao
import dagger.Module
import dagger.Provides

@Module
class PersistenceModule {

    @Provides
    internal fun providesRoomDataDao(manager: DatabaseManager): NoteDao = manager.RoomDataDao()

    @Provides
    internal fun providesRoomDatabaseManager(context: Context): DatabaseManager {
        return DatabaseManager.invoke(context)
    }
}