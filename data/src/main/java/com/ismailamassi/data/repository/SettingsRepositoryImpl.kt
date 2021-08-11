package com.ismailamassi.data.repository

import com.ismailamassi.data.db.settings.SettingsDao
import com.ismailamassi.data.db.settings.SettingsData
import com.ismailamassi.data.mapper.toSettingsData
import com.ismailamassi.data.mapper.toSettingsDto
import com.ismailamassi.domain.model.settings.SettingsDto
import com.ismailamassi.domain.repository.SettingsRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
                val setting: SettingsData = settingsDao.get(0L)
                if (setting != null) {
                    emit(setting.toSettingsDto())
                } else {
                    settingsDao.insert(
                        SettingsData(
                            id = 0L,
                            currentUserId = "",
                            theme = 0,
                            language = "",
                            isFirstTime = true,
                            isLogin = false
                        )
                    )
                    val insertedSetting = settingsDao.get(0L)
                    if (insertedSetting != null) {
                        emit(insertedSetting.toSettingsDto())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun update(settingsDto: SettingsDto): Flow<DataState<SettingsDto>> =
        flow {
            emit(DataState.Loading)
            delay(1500)
            println("update settingsDto$settingsDto")
            try {
                settingsDao.update(settingsDto.toSettingsData())
                emit(DataState.Success(settingsDto))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

}