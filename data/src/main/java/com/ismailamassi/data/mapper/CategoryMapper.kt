package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.category.CategoryData
import com.ismailamassi.domain.model.category.CategoryDto

fun CategoryDto.toData() = CategoryData(
    id = id,
    title = title?:"",
    avatar = avatar?:""
)

fun CategoryData.toDto(recipesCount: Int) = CategoryDto(
    id = id,
    title = title,
    avatar = avatar,
    recipesCount = recipesCount
)

fun List<CategoryDto>.toListData() = this.map { it.toData() }

fun List<CategoryData>.toListDto(recipesCounts: List<Int>) = this.map { it.toDto(0) }
