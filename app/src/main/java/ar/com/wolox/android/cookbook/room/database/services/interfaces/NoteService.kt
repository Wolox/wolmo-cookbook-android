package ar.com.wolox.android.cookbook.room.database.services.interfaces

import ar.com.wolox.android.cookbook.room.database.models.NoteEntity

interface NoteService {
    suspend fun getLastIndex(): Int

    suspend fun getAll(): List<NoteEntity>

    suspend fun findByUser(user: String): NoteEntity

    suspend fun save(data: NoteEntity)

    suspend fun delete(dataEntity: NoteEntity)

    suspend fun deleteAll()
}