package com.ismailamassi.presentation.ui.home


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
class HomeViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val state: SavedStateHandle,
) : BaseViewModel<HomeEvent>() {


    val logout: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onTriggerEvent(eventType: HomeEvent) {
        when (eventType) {
            is HomeEvent.Logout -> onClickLogout()
        }
    }

    private fun onClickLogout() {
        viewModelScope.launch {
            settingsRepository
                .getCurrentSettings()
                .map {
                    settingsRepository.update(it.copy(isLogin = false))
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
                                    logout.value = !dataState.data.isLogin!!
                                }
                            }
                        }
                        .launchIn(viewModelScope)
                }
                .launchIn(viewModelScope)
        }
    }
}