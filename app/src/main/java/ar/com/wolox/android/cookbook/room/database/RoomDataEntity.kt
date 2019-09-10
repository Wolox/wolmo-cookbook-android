package ar.com.wolox.android.cookbook.room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity represents data for a single table row, constructed using an annotated java data object.
 * Each entity is persisted into its own table.
 */
@Entity(tableName = "user_list")
class RoomDataEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = -1

    @ColumnInfo(name = "user")
    lateinit var user: String

    @ColumnInfo(name = "data")
    lateinit var data: String
}