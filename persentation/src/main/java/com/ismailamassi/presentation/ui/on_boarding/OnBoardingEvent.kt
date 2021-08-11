package com.ismailamassi.presentation.ui.on_boarding

import com.ismailamassi.presentation.base.BaseEvent

sealed class OnBoardingEvent : BaseEvent() {
    object SkipOnBoarding : OnBoardingEvent()
}
