package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.settings.SettingsDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun getCurrentSettings(): Flow<SettingsDto>

    suspend fun update(settingsDto: SettingsDto): Flow<DataState<SettingsDto>>

}