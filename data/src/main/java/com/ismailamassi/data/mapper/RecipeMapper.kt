package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.ingredient.IngredientData
import com.ismailamassi.data.db.recipe.RecipeData
import com.ismailamassi.data.db.step.StepData
import com.ismailamassi.domain.model.recipe.RecipeDto

fun RecipeDto.toData() = RecipeData(
    id = id,
    title = title ?: "",
    publisherId = publisherId,
    featuredImage = featuredImage ?: "",
    videoURL = videoURL ?: "",
    likeCount = likeCount,
    categoryId = categoryId,
)

fun RecipeData.toDto(
    ingredients: List<IngredientData> = listOf(),
    steps: List<StepData> = listOf()
) = RecipeDto(
    id = id,
    title = title,
    publisherId = publisherId,
    featuredImage = featuredImage,
    videoURL = videoURL,
    likeCount = likeCount,
    categoryId = categoryId,
    ingredients = ingredients.toListDto(),
    steps = steps.toListDto(),
)

fun List<RecipeDto>.toListData() = this.map { it.toData() }
fun List<RecipeData>.toListDto() = this.map { it.toDto() }


