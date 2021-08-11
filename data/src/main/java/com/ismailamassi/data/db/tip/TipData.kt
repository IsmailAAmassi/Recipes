package com.ismailamassi.data.db.tip

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailamassi.data.db.DatabaseTablesNames

@Entity(tableName = DatabaseTablesNames.TIP_TABLE)
data class TipData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "label") val label: String,
)