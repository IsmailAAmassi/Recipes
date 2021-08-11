package com.ismailamassi.data.repository

import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.favourite.FavouriteDao
import com.ismailamassi.data.mapper.toData
import com.ismailamassi.domain.model.favourite.FavouriteDto
import com.ismailamassi.domain.repository.FavouriteRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteDao: FavouriteDao
) : FavouriteRepository {

    override suspend fun create(favouriteDto: FavouriteDto): Flow<DataState<Long>> = flow {
        try {
            emit(DataState.Loading)
            val result = favouriteDao.insert(favouriteDto.toData())
            if (result != DatabaseErrorName.INSERT_ERROR_CODE) {
                emit(DataState.Success(result))
            } else {
                emit(DataState.Error(Exception(DatabaseErrorName.ERROR_INSERT)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun delete(favouriteDto: FavouriteDto): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            val result = favouriteDao.delete(favouriteDto.recipeId)
            if (result != DatabaseErrorName.DELETE_ERROR_CODE) {
                emit(DataState.Success(result))
            } else {
                emit(DataState.Error(Exception(DatabaseErrorName.ERROR_DELETE)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }
}