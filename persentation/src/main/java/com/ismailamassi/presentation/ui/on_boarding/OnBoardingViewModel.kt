package com.ismailamassi.presentation.ui.on_boarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.repository.SettingsRepository
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
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
        viewModelScope.launch {
            settingsRepository
                .getCurrentSettings()
                .map {
                    settingsRepository.update(it.copy(isFirstTime = false))
                        .map { dataState ->
                            when (dataState) {
                                DataState.Empty -> {

                                }
                                is DataState.Error -> {

                                }
                                DataState.Loading -> {

                                }
                                is DataState.Success -> {

                                }
                            }
                        }
                        .launchIn(viewModelScope)
                }
                .launchIn(viewModelScope)
        }
    }
}