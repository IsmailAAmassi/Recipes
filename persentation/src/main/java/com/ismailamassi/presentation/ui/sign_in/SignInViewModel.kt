package com.ismailamassi.presentation.ui.sign_in


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.repository.SettingsRepository
import com.ismailamassi.domain.repository.UserRepository
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val settingsRepository: SettingsRepository,
    private val state: SavedStateHandle,
) : BaseViewModel<SignInEvent>() {

    private val signInStatusLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onTriggerEvent(eventType: SignInEvent) {
        when (eventType) {
            is SignInEvent.SignIn -> onClickSignIn(eventType.email, eventType.password)
        }
    }

    private fun onClickSignIn(email: String, password: String) {
        // TODO: 8/5/2021 Validation on email and password
        val signInStatus = true
        if (signInStatus) {
            //Update Settings and init
            viewModelScope.launch {
                settingsRepository.getCurrentSettings().map {
                    settingsRepository.update(it.copy(isLogin = signInStatus))
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
                                    signInStatusLiveData.value = dataState.data.isLogin!!
                                }
                            }
                        }
                        .launchIn(viewModelScope)
                }.launchIn(viewModelScope)
            }
        } else {
            //Send error message
            message.value = ""
        }

    }

}