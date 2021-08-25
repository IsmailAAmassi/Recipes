package com.ismailamassi.presentation.ui.recipes_list

import com.ismailamassi.presentation.base.BaseEvent

sealed class RecipesListEvent : BaseEvent() {

    data class GetCategoryRecipes(val categoryId:Long):RecipesListEvent()
}