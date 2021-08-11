package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.settings.SettingsData
import com.ismailamassi.domain.model.settings.SettingsDto

fun SettingsData.toSettingsDto() = SettingsDto(
    id = id,
    currentUserId = currentUserId,
    theme = theme,
    language = language,
    isFirstTime = isFirstTime,
    isLogin = isLogin
)


fun SettingsDto.toSettingsData() = SettingsData(
    id = id ?: 0L,
    currentUserId = currentUserId ?: "",
    theme = theme ?: 0,
    language = language ?: "",
    isFirstTime = isFirstTime ?: true,
    isLogin = isLogin ?: false
)
