package com.ismailamassi.data.db.step

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailamassi.data.db.DatabaseTablesNames

@Entity(tableName = DatabaseTablesNames.STEP_TABLE)
data class StepData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "order") val order: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "last_update") var lastUpdate: Long,

    @ColumnInfo(name = "recipe_id") val recipeId: Long,
)
