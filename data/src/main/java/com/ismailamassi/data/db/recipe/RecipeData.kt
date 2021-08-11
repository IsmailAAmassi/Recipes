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
    @ColumnInfo(name = "publisher_id") val publisherId: String,
    @ColumnInfo(name = "featured_image") val featuredImage: String,
    @ColumnInfo(name = "video_url") val videoURL: String,
    @ColumnInfo(name = "like_count") val likeCount: Int,

    @ColumnInfo(name = "category_id") val categoryId: Long,
)
