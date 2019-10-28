package ar.com.wolox.android.cookbook.room.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Entity represents data for a single table row, constructed using an annotated java data object.
 * Each entity is persisted into its own table.
 */
@Entity
data class NoteEntity(
    @PrimaryKey
    val id: Int,

    val user: String,

    val data: String
) : Serializable