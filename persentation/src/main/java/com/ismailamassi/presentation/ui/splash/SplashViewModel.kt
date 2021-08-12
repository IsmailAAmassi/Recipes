package com.ismailamassi.presentation.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.model.settings.SettingsDto
import com.ismailamassi.domain.model.tip.TipDto
import com.ismailamassi.domain.repository.SettingsRepository
import com.ismailamassi.domain.repository.TipRepository
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.base.BaseViewModel
import com.ismailamassi.presentation.utils.EmptyMessages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tipRepository: TipRepository,
    private val settingsRepository: SettingsRepository,
    private val state: SavedStateHandle,
) : BaseViewModel<SplashEvent>() {

    var settingsLiveData = MutableLiveData<SettingsDto>()
    var randomTipLiveData = MutableLiveData<TipDto?>()
    var updateDataBaseLiveData = MutableLiveData<Boolean>()

    override fun onTriggerEvent(eventType: SplashEvent) {
        when (eventType) {
            SplashEvent.GetRandomTip -> getRandomTip()
            SplashEvent.GetSettings -> getSettings()
            SplashEvent.UpdateDatabase -> updateDatabaseTables()
        }
    }

    private fun getSettings() {
        viewModelScope.launch {
            settingsRepository.getCurrentSettings()
                .map {
                    settingsLiveData.postValue(it)
                }.launchIn(viewModelScope)
        }
    }

    private fun getRandomTip() {
        viewModelScope.launch {
            tipRepository.getRandomTip().map {
                when (it) {
                    DataState.Empty -> {
                        _loadingLiveData.postValue(false)
                        _emptyLiveData.postValue(EmptyMessages.NO_RANDOM_TIP)
                    }
                    is DataState.Error -> {
                        _loadingLiveData.postValue(false)
                        _errorLiveData.postValue(it.exception)
                    }
                    DataState.Loading -> {
                        _loadingLiveData.postValue(true)
                    }
                    is DataState.Success -> {
                        _loadingLiveData.postValue(false)
                        randomTipLiveData.postValue(it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun updateDatabaseTables() {
        viewModelScope.launch {
            delay(3000)
            updateDataBaseLiveData.postValue(true)
        }
    }
}