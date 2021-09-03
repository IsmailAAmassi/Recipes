package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.step.StepData
import com.ismailamassi.domain.model.recipe.StepDto

fun StepDto.toData() = StepData(
    id = id,
    order = order,
    description = description ?: "",
    lastUpdate = lastUpdate,
    recipeId = recipeId,
)

fun StepData.toDto() = StepDto(
    id = id,
    order = order,
    description = description,
    lastUpdate = lastUpdate,
    recipeId = recipeId,
)

fun List<StepDto>.toListData() = this.map { it.toData() }
fun List<StepData>.toListDto() = this.map { it.toDto() }