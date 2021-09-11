package com.ismailamassi.presentation.ui.home

import com.ismailamassi.presentation.base.BaseEvent

sealed class HomeEvent : BaseEvent() {
    object GetHomeRecipes : HomeEvent()
    object GetCategories : HomeEvent()
}