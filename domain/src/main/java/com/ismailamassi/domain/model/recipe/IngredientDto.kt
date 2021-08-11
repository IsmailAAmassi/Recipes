package com.ismailamassi.domain.model.recipe

data class IngredientDto(
    val id: Long,
    var name: String,
    var qty: String,
    var unit: String,
    var recipeId:Long
)
