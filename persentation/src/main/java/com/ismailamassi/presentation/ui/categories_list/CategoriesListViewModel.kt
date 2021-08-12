package com.ismailamassi.presentation.ui.categories_list


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
class CategoriesListViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val state: SavedStateHandle,
) : BaseViewModel<CategoriesListEvent>() {


    val logout: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onTriggerEvent(eventType: CategoriesListEvent) {
        when (eventType) {
            CategoriesListEvent.Logout -> onClickLogout()
            CategoriesListEvent.GetCategoriesList -> TODO()
        }
    }

    private fun onClickLogout() {
        viewModelScope.launch {
            settingsRepository
                .getCurrentSettings()
                .map {
                    settingsRepository.update(it.copy(isLogin = false))
                        .map { dataState ->

                        }
                        .launchIn(viewModelScope)
                }
                .launchIn(viewModelScope)
        }
    }
}