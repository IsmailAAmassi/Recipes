package com.ismailamassi.data.repository

import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.step.StepDao
import com.ismailamassi.data.mapper.toDto
import com.ismailamassi.data.mapper.toDtoList
import com.ismailamassi.domain.model.recipe.StepDto
import com.ismailamassi.domain.repository.StepRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StepRepositoryImpl @Inject constructor(
    private val stepDao: StepDao
) : StepRepository {
    override suspend fun create(stepDto: StepDto): Flow<DataState<Long>> =
        flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE

        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun update(stepDto: StepDto): Flow<DataState<Int>> =
        flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE

        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun delete(stepId: Long): Flow<DataState<Int>> =
        flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE

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

    override suspend fun createList(stepsList: List<StepDto>): Flow<DataState<Int>> =
        flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE

        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateList(stepsList: List<StepDto>): Flow<DataState<Int>> =
        flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE

        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteList(stepsIds: List<Long>): Flow<DataState<Int>> =
        flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE

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
                emit(DataState.Success(result.toDtoList()))
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
                emit(DataState.Success(result.toDtoList()))
            } else {
                emit(DataState.Empty)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteAll(): Flow<DataState<Int>> =
        flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE

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
                emit(DataState.Success(result.toDtoList()))
            } else {
                emit(DataState.Empty)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}