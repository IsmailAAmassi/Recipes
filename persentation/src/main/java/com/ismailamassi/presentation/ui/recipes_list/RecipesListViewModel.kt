package com.ismailamassi.presentation.ui.recipes_list

import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor() : BaseViewModel<RecipesListEvent>() {

    override fun onTriggerEvent(eventType: RecipesListEvent) {

    }
}