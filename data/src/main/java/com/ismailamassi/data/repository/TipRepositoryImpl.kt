package com.ismailamassi.data.repository

import com.ismailamassi.data.api.ApiErrorName
import com.ismailamassi.data.api.tip.TipApi
import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.tip.TipDao
import com.ismailamassi.data.mapper.toData
import com.ismailamassi.data.mapper.toDto
import com.ismailamassi.data.mapper.toListData
import com.ismailamassi.data.mapper.toListDto
import com.ismailamassi.domain.model.tip.TipDto
import com.ismailamassi.domain.repository.TipRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TipRepositoryImpl @Inject constructor(
    private val tipDao: TipDao,
    private val tipApi: TipApi
) : TipRepository {

    override suspend fun create(tipDto: TipDto): Flow<DataState<TipDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = tipApi.create("", tipDto)
                val dbResult = tipDao.insert(tipDto.toData())
                if (dbResult != DatabaseErrorName.INSERT_ERROR_CODE) {
                    emit(DataState.Success(apiResult))
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.INSERT_ERROR_MESSAGE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun update(tipDto: TipDto): Flow<DataState<TipDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = tipApi.update("", tipDto)
                val dbResult = tipDao.update(tipDto.toData())
                if (dbResult != DatabaseErrorName.UPDATE_ERROR_CODE) {
                    emit(DataState.Success(apiResult))
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.UPDATE_ERROR_MESSAGE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun delete(tipId: Long): Flow<DataState<TipDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = tipApi.delete("", tipId)
                val dbResult = tipDao.delete(tipId)
                if (apiResult != null) {
                    if (dbResult != DatabaseErrorName.UPDATE_ERROR_CODE) {
                        emit(DataState.Success(apiResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.DELETE_ERROR_MESSAGE)))
                    }
                } else {
                    emit(DataState.Error(Exception(ApiErrorName.ERROR_DELETE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun get(id: Long): Flow<DataState<TipDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = tipDao.get(id)
                if (result != DatabaseErrorName.GET_ERROR) {
                    emit(DataState.Success(result.toDto()))
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.ERROR_GET_MESSAGE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun createList(
        tipsList: List<TipDto>,
        isUserDoAction: Boolean
    ): Flow<DataState<List<Long>>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = tipApi.createList("", tipsList)
                    tipsList.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = tipDao.insert(tipsList.toListData())
                    if (dbResult.size == tipsList.size) {
                        emit(DataState.Success(dbResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_INSERT_ERROR_MESSAGE)))
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
        tipsList: List<TipDto>,
        isUserDoAction: Boolean
    ): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = tipApi.updateList("", tipsList)
                    tipsList.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = tipDao.update(tipsList.toListData())
                    if (dbResult == tipsList.size) {
                        emit(DataState.Success(dbResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_INSERT_ERROR_MESSAGE)))
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
        tipsIds: List<Long>,
        isUserDoAction: Boolean
    ): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = tipApi.deleteList("", tipsIds)
                    tipsIds.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = tipDao.delete(tipsIds)
                    if (dbResult == tipsIds.size) {
                        emit(DataState.Success(dbResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_DELETE_ERROR_MESSAGE)))
                    }
                } else {
                    emit(DataState.Error(Exception(ApiErrorName.MULTIPLE_ERROR_DELETE)))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getList(tipsIds: List<Long>): Flow<DataState<List<TipDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = tipDao.get(tipsIds)
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

    override suspend fun getAll(): Flow<DataState<List<TipDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = tipDao.get()
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
                    val apiResult = tipApi.deleteAll("")
                    apiResult.isNotEmpty()
                } else true

                if (apiStatus) {
                    val dbResult = tipDao.drop()
                    val remaining = tipDao.count()
                    if (remaining == 0) {
                        emit(DataState.Success(dbResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_DELETE_ERROR_MESSAGE)))
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
                val result = tipDao.count()
                emit(DataState.Success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun search(query: String): Flow<DataState<List<TipDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = tipDao.search(query)
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

    override suspend fun getRandomTip(): Flow<DataState<TipDto>> =
        flow {
            try {
                emit(DataState.Loading)
                delay(1000)
                val result = tipDao.getRandom()
                if (result != DatabaseErrorName.GET_ERROR) {
                    emit(DataState.Success(result.toDto()))
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.ERROR_GET_MESSAGE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)
}