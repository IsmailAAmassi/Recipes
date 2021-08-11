package com.ismailamassi.data.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailamassi.data.db.DatabaseTablesNames

@Entity(tableName = DatabaseTablesNames.USER_TABLE)
data class UserData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_url") val imageURL: String,
    @ColumnInfo(name = "token") val token: String,
)