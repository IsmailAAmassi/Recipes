package com.ismailamassi.data.repository

import com.ismailamassi.data.db.settings.SettingsDao
import com.ismailamassi.data.db.settings.SettingsData
import com.ismailamassi.data.mapper.toData
import com.ismailamassi.data.mapper.toDto
import com.ismailamassi.domain.model.settings.SettingsDto
import com.ismailamassi.domain.repository.SettingsRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsDao: SettingsDao
) : SettingsRepository {
    override suspend fun getCurrentSettings(): Flow<SettingsDto> =
        flow {
            try {
                val setting = settingsDao.get(0L)
                if (setting != null) {
                    emit(setting.toDto())
                } else {
                    settingsDao.insert(
                        SettingsData(
                            id = 0L,
                            currentUserId = -1,
                            currentUserToken = "",
                            theme = -1,
                            language = "",
                            isFirstTime = true,
                            isLogin = false
                        )
                    )
                    val insertedSetting = settingsDao.get(0L)
                    if (insertedSetting != null) {
                        emit(insertedSetting.toDto())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun update(settingsDto: SettingsDto): Flow<DataState<SettingsDto>> =
        flow {
            emit(DataState.Loading)
            try {
                settingsDao.update(settingsDto.toData())
                emit(DataState.Success(settingsDto))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun updateCurrentUserId(currentUserId: Long): Flow<DataState<SettingsDto>> =
        flow {
            emit(DataState.Loading)
            try {
                getCurrentSettings().collect {settings->
                    settingsDao.update(settings.apply { this.currentUserId = currentUserId }.toData())
                }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun updateCurrentUserId(currentUserToken: String): Flow<DataState<SettingsDto>> =
        flow {
            emit(DataState.Loading)
            try {
                getCurrentSettings().collect {settings->
                    settingsDao.update(settings.apply { this.currentUserToken = currentUserToken }.toData())
                }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)
}