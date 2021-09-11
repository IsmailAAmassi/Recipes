package com.ismailamassi.presentation.ui.tips_list

import com.ismailamassi.presentation.base.BaseEvent

sealed class TipEvent : BaseEvent() {
    object GetTips : TipEvent()
}