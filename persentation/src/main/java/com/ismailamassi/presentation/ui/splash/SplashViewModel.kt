package com.ismailamassi.presentation.ui.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.repository.SettingsRepository
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val state: SavedStateHandle,
) : BaseViewModel<SplashEvent>() {

    override fun onTriggerEvent(eventType: SplashEvent) {
        when (eventType) {
            is SplashEvent.GetSettings -> getConfigData()
            is SplashEvent.GetRandomTip -> getRandomTip()
            is SplashEvent.UpdateDatabase -> updateDatabaseTables()
        }
    }

    private fun getConfigData() {
        viewModelScope.launch {
            settingsRepository.getCurrentSettings()
                .map {

                }
                .launchIn(viewModelScope)
        }
    }

    private fun getRandomTip() {

    }

    private fun updateDatabaseTables() {

    }
}