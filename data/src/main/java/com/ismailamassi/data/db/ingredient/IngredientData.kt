package com.ismailamassi.data.db.ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailamassi.data.db.DatabaseTablesNames

@Entity(tableName = DatabaseTablesNames.INGREDIENT_TABLE)
data class IngredientData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "qty") val qty: String,
    @ColumnInfo(name = "unit") val unit: String,

    @ColumnInfo(name = "recipe_id") val recipeId: Long
)