package com.ismailamassi.presentation.ui.login

import com.ismailamassi.presentation.base.BaseEvent

sealed class LoginEvent : BaseEvent() {
    data class Login(var email: String, val password: String) : LoginEvent()
}
