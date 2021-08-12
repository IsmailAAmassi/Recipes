package com.ismailamassi.presentation.ui.categories_list

import com.ismailamassi.presentation.base.BaseEvent

sealed class CategoriesListEvent : BaseEvent() {
    object GetCategoriesList : CategoriesListEvent()
    object Logout : CategoriesListEvent()
}