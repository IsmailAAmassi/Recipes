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


    val skipOnBoardingStatus: MutableLiveData<Boolean> = MutableLiveData(false)

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
                                is DataState.Empty -> {
                                    loading.value = false
                                }
                                is DataState.Error -> {
                                    loading.value = false
                                    error.value = dataState.exception
                                }
                                DataState.Loading -> {
                                    loading.value = true
                                }
                                is DataState.Success -> {
                                    loading.value = false
                                    skipOnBoardingStatus.value = !dataState.data.isFirstTime!!
                                }
                            }
                        }
                        .launchIn(viewModelScope)
                }
                .launchIn(viewModelScope)
        }
    }
}