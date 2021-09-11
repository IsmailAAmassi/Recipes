package com.ismailamassi.presentation.ui.search

import com.ismailamassi.presentation.base.BaseEvent

sealed class SearchEvent : BaseEvent() {
    data class SearchQuery(val query: String) : SearchEvent()
}