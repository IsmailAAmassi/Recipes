package com.ismailamassi.data.repository

import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.favourite.FavouriteDao
import com.ismailamassi.data.mapper.toData
import com.ismailamassi.data.mapper.toListDto
import com.ismailamassi.domain.model.favourite.FavouriteDto
import com.ismailamassi.domain.repository.FavouriteRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteDao: FavouriteDao
) : FavouriteRepository {

    override suspend fun create(favouriteDto: FavouriteDto): Flow<DataState<Long>> =
        flow {
        try {
            emit(DataState.Loading)
            val result = favouriteDao.insert(favouriteDto.toData())
            if (result != DatabaseErrorName.INSERT_ERROR_CODE) {
                emit(DataState.Success(result))
            } else {
                emit(DataState.Error(Exception(DatabaseErrorName.INSERT_ERROR_MESSAGE)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun delete(id: Long): Flow<DataState<Int>> =
        flow {
        try {
            emit(DataState.Loading)
            val result = favouriteDao.delete(id)
            if (result != DatabaseErrorName.DELETE_ERROR_CODE) {
                emit(DataState.Success(result))
            } else {
                emit(DataState.Error(Exception(DatabaseErrorName.DELETE_ERROR_MESSAGE)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAll(): Flow<DataState<List<FavouriteDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = favouriteDao.get()
                if (result != DatabaseErrorName.GET_ERROR) {
                    emit(DataState.Success(result.toListDto()))
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.INSERT_ERROR_MESSAGE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun isRecipeFavourite(recipeId: Long): Flow<DataState<Boolean>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = favouriteDao.get(recipeId)
                if (result != DatabaseErrorName.GET_ERROR) {
                    emit(DataState.Success(true))
                } else {
                    emit(DataState.Success(false))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)
}