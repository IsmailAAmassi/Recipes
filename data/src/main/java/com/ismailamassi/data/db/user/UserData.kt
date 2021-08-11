package com.ismailamassi.data.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailamassi.data.db.DatabaseTablesNames

@Entity(tableName = DatabaseTablesNames.USER_TABLE)
data class UserData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
)