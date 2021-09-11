package com.ismailamassi.presentation.ui.on_boarding

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val state: SavedStateHandle,
) : BaseViewModel<OnBoardingEvent>() {


    override fun onTriggerEvent(eventType: OnBoardingEvent) {
        viewModelScope.launch {
            when (eventType) {
                is OnBoardingEvent.SkipOnBoarding -> onClickSkipOnBoarding()
            }
        }
    }

    private fun onClickSkipOnBoarding() {

    }
}