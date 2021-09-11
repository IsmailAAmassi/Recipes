package com.ismailamassi.presentation.ui.tips_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.model.tip.TipDto
import com.ismailamassi.domain.repository.TipRepository
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipViewModel @Inject constructor(
    private val tipRepository: TipRepository
) : BaseViewModel<TipEvent>() {

    val tipLiveData = MutableLiveData<List<TipDto>?>()

    override fun onTriggerEvent(eventType: TipEvent) {
        when (eventType) {
            TipEvent.GetTips -> getTips()
        }
    }

    private fun getTips() {
        viewModelScope.launch {
            tipRepository.getAll().map {
                when (it) {
                    DataState.Empty -> {
                        _loadingLiveData.postValue(false)
                        _emptyLiveData.postValue("No Tips")
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
                        tipLiveData.postValue(it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}