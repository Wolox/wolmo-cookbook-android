package ar.com.wolox.android.cookbook.common.di.room

import ar.com.wolox.android.cookbook.room.database.persitence.NoteDao
import ar.com.wolox.android.cookbook.room.database.services.NoteServiceImplementation
import ar.com.wolox.android.cookbook.room.database.services.interfaces.NoteService
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {
    @Provides
    internal fun providesRoomService(serviceImplementation: NoteServiceImplementation): NoteService = serviceImplementation

    @Provides
    internal fun providesRoomImplementation(dao: NoteDao): NoteServiceImplementation = NoteServiceImplementation(dao)
}