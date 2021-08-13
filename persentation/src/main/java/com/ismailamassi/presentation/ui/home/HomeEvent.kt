package com.ismailamassi.presentation.ui.home

import com.ismailamassi.presentation.base.BaseEvent
import com.ismailamassi.presentation.ui.splash.SplashEvent

sealed class HomeEvent:BaseEvent() {
    object GetRandomCategories : HomeEvent()
    object GetRandomRecipes : HomeEvent()
    object GetRandomTip : HomeEvent()
}