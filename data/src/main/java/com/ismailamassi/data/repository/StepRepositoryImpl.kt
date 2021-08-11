package com.ismailamassi.data.repository

import com.ismailamassi.data.api.ApiErrorName
import com.ismailamassi.data.api.step.StepApi
import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.step.StepDao
import com.ismailamassi.data.mapper.toData
import com.ismailamassi.data.mapper.toDto
import com.ismailamassi.data.mapper.toListData
import com.ismailamassi.data.mapper.toListDto
import com.ismailamassi.domain.model.recipe.StepDto
import com.ismailamassi.domain.repository.StepRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StepRepositoryImpl @Inject constructor(
    private val stepDao: StepDao,
    private val stepApi: StepApi
) : StepRepository {
    override suspend fun create(stepDto: StepDto): Flow<DataState<StepDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = stepApi.create("", stepDto)
                val dbResult = stepDao.insert(stepDto.toData())
                if (dbResult != DatabaseErrorName.INSERT_ERROR_CODE) {
                    emit(DataState.Success(apiResult))
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.ERROR_INSERT)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun update(stepDto: StepDto): Flow<DataState<StepDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = stepApi.update("", stepDto)
                val dbResult = stepDao.update(stepDto.toData())
                if (dbResult != DatabaseErrorName.UPDATE_ERROR_CODE) {
                    emit(DataState.Success(apiResult))
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.ERROR_UPDATE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun delete(stepId: Long): Flow<DataState<StepDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = stepApi.delete("", stepId)
                val dbResult = stepDao.delete(stepId)
                if (apiResult != null) {
                    if (dbResult != DatabaseErrorName.UPDATE_ERROR_CODE) {
                        emit(DataState.Success(apiResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.ERROR_DELETE)))
                    }
                } else {
                    emit(DataState.Error(Exception(ApiErrorName.ERROR_DELETE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun get(id: Long): Flow<DataState<StepDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = stepDao.get(id)
                if (result != DatabaseErrorName.GET_ERROR) {
                    emit(DataState.Success(result.toDto()))
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.ERROR_GET)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun createList(
        stepsList: List<StepDto>,
        isUserDoAction: Boolean
    ): Flow<DataState<List<Long>>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = stepApi.createList("", stepsList)
                    stepsList.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = stepDao.insert(stepsList.toListData())
                    if (dbResult.size == stepsList.size) {
                        emit(DataState.Success(dbResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_ERROR_INSERT)))
                    }
                } else {
                    emit(DataState.Error(Exception(ApiErrorName.MULTIPLE_ERROR_INSERT)))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun updateList(
        stepsList: List<StepDto>,
        isUserDoAction: Boolean
    ): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = stepApi.updateList("", stepsList)
                    stepsList.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = stepDao.update(stepsList.toListData())
                    if (dbResult == stepsList.size) {
                        emit(DataState.Success(dbResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_ERROR_INSERT)))
                    }
                } else {
                    emit(DataState.Error(Exception(ApiErrorName.MULTIPLE_ERROR_INSERT)))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun deleteList(
        stepsIds: List<Long>,
        isUserDoAction: Boolean
    ): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = stepApi.deleteList("", stepsIds)
                    stepsIds.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = stepDao.delete(stepsIds)
                    if (dbResult == stepsIds.size) {
                        emit(DataState.Success(dbResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_ERROR_DELETE)))
                    }
                } else {
                    emit(DataState.Error(Exception(ApiErrorName.MULTIPLE_ERROR_DELETE)))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getList(stepsIds: List<Long>): Flow<DataState<List<StepDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = stepDao.get(stepsIds)
                if (result.isNotEmpty()) {
                    emit(DataState.Success(result.toListDto()))
                } else {
                    emit(DataState.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getAll(): Flow<DataState<List<StepDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = stepDao.get()
                if (result.isNotEmpty()) {
                    emit(DataState.Success(result.toListDto()))
                } else {
                    emit(DataState.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun deleteAll(
        isUserDoAction: Boolean
    ): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = stepApi.deleteAll("")
                    apiResult.isNotEmpty()
                } else true

                if (apiStatus) {
                    val dbResult = stepDao.drop()
                    val remaining = stepDao.count()
                    if (remaining == 0) {
                        emit(DataState.Success(dbResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_ERROR_DELETE)))
                    }
                }else{
                    emit(DataState.Error(Exception(ApiErrorName.MULTIPLE_ERROR_DELETE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getCount(): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = stepDao.count()
                emit(DataState.Success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun search(query: String): Flow<DataState<List<StepDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = stepDao.search(query)
                if (result.isNotEmpty()) {
                    emit(DataState.Success(result.toListDto()))
                } else {
                    emit(DataState.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)
}