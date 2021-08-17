package com.ismailamassi.presentation.ui.home

import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<HomeEvent>() {

    override fun onTriggerEvent(eventType: HomeEvent) {

    }
}