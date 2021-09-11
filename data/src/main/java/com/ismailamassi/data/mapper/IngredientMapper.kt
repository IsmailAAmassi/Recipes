package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.ingredient.IngredientData
import com.ismailamassi.domain.model.recipe.IngredientDto

fun IngredientDto.toData() = IngredientData(
    id = id,
    name = name ?: "",
    qty = qty ?: "",
    unit = unit ?: "",
    lastUpdate = lastUpdate,
    recipeId = recipeId,
)

fun IngredientData.toDto() = IngredientDto(
    id = id,
    name = name,
    qty = qty,
    unit = unit,
    lastUpdate = lastUpdate,
    recipeId = recipeId,
)

fun List<IngredientDto>.toListData() = this.map { it.toData() }
fun List<IngredientData>.toListDto() = this.map { it.toDto() }