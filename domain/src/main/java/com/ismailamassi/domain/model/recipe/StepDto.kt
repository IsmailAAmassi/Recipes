package com.ismailamassi.domain.model.recipe

data class StepDto(
    var id: Long,
    var order: Int,
    var description: String,
    var recipeId:Long
)