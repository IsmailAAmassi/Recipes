package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.settings.SettingsData
import com.ismailamassi.domain.model.settings.SettingsDto

fun SettingsDto.toData() = SettingsData(
    id = id ?: 0L,
    currentUserId = currentUserId?:0L,
    currentUserToken = currentUserToken ?: "",
    theme = theme ?: 0,
    language = language ?: "",
    isFirstTime = isFirstTime ?: true,
    isLogin = isLogin ?: false
)


fun SettingsData.toDto() = SettingsDto(
    id = id,
    currentUserId = currentUserId,
    currentUserToken = currentUserToken,
    theme = theme,
    language = language,
    isFirstTime = isFirstTime,
    isLogin = isLogin
)
