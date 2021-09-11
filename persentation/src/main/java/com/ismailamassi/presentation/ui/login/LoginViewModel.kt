package com.ismailamassi.presentation.ui.login


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.repository.UserRepository
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val state: SavedStateHandle,
) : BaseViewModel<LoginEvent>() {

    private val signInStatusLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onTriggerEvent(eventType: LoginEvent) {
        when (eventType) {
            is LoginEvent.Login -> onClickSignIn(eventType.email, eventType.password)
        }
    }

    private fun onClickSignIn(email: String, password: String) {

    }

}