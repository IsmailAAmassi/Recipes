package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.ingredient.IngredientData
import com.ismailamassi.domain.model.recipe.IngredientDto

fun IngredientDto.toData() = IngredientData(
    id = id,
    name = name?:"",
    qty = qty?:"",
    unit = unit?:"",
    recipeId = recipeId,
)

fun IngredientData.toDto() = IngredientDto(
    id = id,
    name = name,
    qty = qty,
    unit = unit,
    recipeId = recipeId,
)

fun List<IngredientDto>.toDataList() = this.map { it.toData()}
fun List<IngredientData>.toDtoList() = this.map { it.toDto() }