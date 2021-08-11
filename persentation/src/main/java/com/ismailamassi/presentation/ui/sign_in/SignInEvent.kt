package com.ismailamassi.presentation.ui.sign_in

import com.ismailamassi.presentation.base.BaseEvent

sealed class SignInEvent : BaseEvent() {
    data class SignIn(var email: String, val password: String) : SignInEvent()
}
