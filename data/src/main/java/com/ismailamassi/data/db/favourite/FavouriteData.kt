package com.ismailamassi.data.db.favourite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailamassi.data.db.DatabaseTablesNames

@Entity(tableName = DatabaseTablesNames.FAVOURITE_TABLE)
data class FavouriteData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,

    @ColumnInfo(name = "recipe_id") val recipeId: Long,
)