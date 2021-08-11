package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.ingredient.IngredientData
import com.ismailamassi.data.db.recipe.RecipeData
import com.ismailamassi.data.db.step.StepData
import com.ismailamassi.domain.model.recipe.RecipeDto

fun RecipeDto.toData() = RecipeData(
    id = id,
    title = title ?: "",
    publisherId = publisherId ?: "",
    featuredImage = featuredImage ?: "",
    videoURL = videoURL ?: "",
    likeCount = likeCount,
    categoryId = categoryId,
)

fun RecipeData.toDto(ingredients: List<IngredientData>, steps: List<StepData>) = RecipeDto(
    id = id,
    title = title,
    publisherId = publisherId,
    featuredImage = featuredImage,
    videoURL = videoURL,
    likeCount = likeCount,
    categoryId = categoryId,
    ingredients = ingredients.toDtoList(),
    steps = steps.toDtoList(),
)
