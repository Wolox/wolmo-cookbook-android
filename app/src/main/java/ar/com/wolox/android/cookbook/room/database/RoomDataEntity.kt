package ar.com.wolox.android.cookbook.room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personal_notes")
class RoomDataEntity {

    @PrimaryKey(autoGenerate = true) var id: Int = 0

    @ColumnInfo(name = "user")
    lateinit var user: String

    @ColumnInfo(name = "data")
    lateinit var data: String
}