package com.ismailamassi.presentation.ui.home

import java.io.Serializable

sealed class RecipeListType() : Serializable {


    object MostLovedRecipes : RecipeListType()
    object BestCollectionRecipes : RecipeListType()
    object MostViewedRecipes : RecipeListType()
    data class CategoryRecipes(val categoryId: Long) : RecipeListType()

}
