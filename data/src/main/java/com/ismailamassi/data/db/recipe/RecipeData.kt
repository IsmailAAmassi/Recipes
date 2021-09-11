package com.ismailamassi.data.db.recipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailamassi.data.db.DatabaseTablesNames

@Entity(tableName = DatabaseTablesNames.RECIPE_TABLE)
data class RecipeData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "publisher_id") val publisherId: Long,
    @ColumnInfo(name = "featured_image") val featuredImage: String,
    @ColumnInfo(name = "video_url") val videoURL: String,

    @ColumnInfo(name = "preparation_time") var preparationTime: String?,
    @ColumnInfo(name = "cooking_time") var cookingTime: String?,
    @ColumnInfo(name = "serving") var serving: String?,

    @ColumnInfo(name = "position") val position: Int,
    @ColumnInfo(name = "like_count") val likeCount: Int,
    @ColumnInfo(name = "last_update") var lastUpdate: Long,

    @ColumnInfo(name = "category_id") val categoryId: Long,
)
