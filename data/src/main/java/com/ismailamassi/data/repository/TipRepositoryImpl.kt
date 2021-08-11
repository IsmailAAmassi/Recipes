package com.ismailamassi.data.repository

import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.tip.TipDao
import com.ismailamassi.data.mapper.toDto
import com.ismailamassi.data.mapper.toListDto
import com.ismailamassi.domain.model.tip.TipDto
import com.ismailamassi.domain.repository.TipRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TipRepositoryImpl @Inject constructor(
    private val tipDao: TipDao
) : TipRepository {

    override suspend fun create(tipDto: TipDto): Flow<DataState<Long>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun update(tipDto: TipDto): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun delete(tipId: Long): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun get(id: Long): Flow<DataState<TipDto>> = flow {
        try {
            emit(DataState.Loading)
            val result = tipDao.get(id)
            if (result != DatabaseErrorName.GET_ERROR) {
                emit(DataState.Success(result.toDto()))
            } else {
                emit(DataState.Error(Exception(DatabaseErrorName.ERROR_GET)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun createList(tipsList: List<TipDto>): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun updateList(tipsList: List<TipDto>): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun deleteList(tipsIds: List<Long>): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun getList(tipsIds: List<Long>): Flow<DataState<List<TipDto>>> = flow {
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
    }

    override suspend fun getAll(): Flow<DataState<List<TipDto>>> = flow {
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
    }

    override suspend fun deleteAll(): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun getCount(): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            val result = tipDao.count()
            emit(DataState.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun search(query: String): Flow<DataState<List<TipDto>>> = flow {
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
    }

    override suspend fun getRandomTip(): Flow<DataState<TipDto>> = flow {
        try {
            emit(DataState.Loading)
            val result = tipDao.getRandom()
            if (result != DatabaseErrorName.GET_ERROR) {
                emit(DataState.Success(result.toDto()))
            } else {
                emit(DataState.Error(Exception(DatabaseErrorName.ERROR_GET)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }
}