package com.ismailamassi.data.db.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailamassi.data.db.DatabaseTablesNames

@Entity(tableName = DatabaseTablesNames.CATEGORY_TABLE)
data class CategoryData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "avatar") val avatar: String,
    @ColumnInfo(name = "last_update") var lastUpdate: Long,
    @ColumnInfo(name = "position") var position: Int,
)