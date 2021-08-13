package com.ismailamassi.presentation.ui.splash

sealed class SplashEvent {
    object GetSettings : SplashEvent()
    object UpdateDatabase : SplashEvent()
}