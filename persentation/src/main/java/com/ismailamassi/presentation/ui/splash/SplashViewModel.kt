package com.ismailamassi.presentation.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.model.tip.TipDto
import com.ismailamassi.domain.repository.TipRepository
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.base.BaseViewModel
import com.ismailamassi.presentation.utils.EmptyMessages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val state: SavedStateHandle,
) : BaseViewModel<SplashEvent>() {

    var randomTipLiveData = MutableLiveData<TipDto?>()
    var updateDataBaseLiveData = MutableLiveData<Boolean>()

    override fun onTriggerEvent(eventType: SplashEvent) {
        when (eventType) {
            SplashEvent.UpdateDatabase -> updateDatabaseTables()
        }
    }


    private fun updateDatabaseTables() {
        viewModelScope.launch {
            // TODO: 8/25/2021 Add Update Code Here
            updateDataBaseLiveData.postValue(true)
        }
    }
}