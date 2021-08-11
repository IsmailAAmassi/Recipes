package com.ismailamassi.data.db.favourite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailamassi.data.db.DatabaseTablesNames

@Entity(tableName = DatabaseTablesNames.FAVOURITE_TABLE)
data class FavouriteData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "recipe_id") val recipeId: Long,
)