package com.ismailamassi.presentation.ui.recipe_info

sealed class RecipeInfoEvent {
    data class GetRecipeInfo(val recipeId: Long):RecipeInfoEvent()
}