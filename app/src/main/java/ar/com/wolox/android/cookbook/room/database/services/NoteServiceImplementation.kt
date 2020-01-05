package ar.com.wolox.android.cookbook.room.database.services

import ar.com.wolox.android.cookbook.room.database.models.NoteEntity
import ar.com.wolox.android.cookbook.room.database.persitence.NoteDao
import ar.com.wolox.android.cookbook.room.database.services.interfaces.NoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteServiceImplementation(val dao: NoteDao) : NoteService {

    override suspend fun getLastIndex(): Int = withContext(Dispatchers.IO) {
        dao.getLastIndex()
    }

    override suspend fun getAll(): List<NoteEntity> = withContext(Dispatchers.IO) {
        dao.getAll()
    }

    override suspend fun findByUser(user: String): NoteEntity = withContext(Dispatchers.IO) {
        dao.findByUser(user)
    }

    override suspend fun save(data: NoteEntity) {
        withContext(Dispatchers.IO) {
            dao.save(data)
        }
    }

    override suspend fun delete(dataEntity: NoteEntity) {
        withContext(Dispatchers.IO) {
            dao.delete(dataEntity)
        }
    }

    override suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            dao.deleteAll()
        }
    }
}