package com.ismailamassi.data.repository

import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.ingredient.IngredientDao
import com.ismailamassi.data.mapper.toDto
import com.ismailamassi.data.mapper.toDtoList
import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.domain.repository.IngredientRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val ingredientDao: IngredientDao
) : IngredientRepository {

    override suspend fun create(ingredientDto: IngredientDto): Flow<DataState<Long>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }

    }

    override suspend fun update(ingredientDto: IngredientDto): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun delete(ingredientId: Long): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun get(id: Long): Flow<DataState<IngredientDto>> = flow {
        try {
            emit(DataState.Loading)
            val result = ingredientDao.get(id)
            if (result != null) {
                emit(DataState.Success(result.toDto()))
            } else {
                emit(DataState.Error(Exception(DatabaseErrorName.ERROR_GET)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun createList(ingredientsList: List<IngredientDto>): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }

    override suspend fun updateList(ingredientsList: List<IngredientDto>): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }

    override suspend fun deleteList(ingredientsIds: List<Long>): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun getList(ingredientsIds: List<Long>): Flow<DataState<List<IngredientDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = ingredientDao.get(ingredientsIds)
                if (result.isNotEmpty()){
                    emit(DataState.Success(result.toDtoList()))
                }else{
                    emit(DataState.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }

    override suspend fun getAll(): Flow<DataState<List<IngredientDto>>> = flow {
        try {
            emit(DataState.Loading)
            val result = ingredientDao.get()
            if (result.isNotEmpty()){
                emit(DataState.Success(result.toDtoList()))
            }else{
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
            val result = ingredientDao.count()
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun search(query: String): Flow<DataState<List<IngredientDto>>> = flow {
        try {
            emit(DataState.Loading)
            val result = ingredientDao.search(query)
            if (result.isNotEmpty()){
                emit(DataState.Success(result.toDtoList()))
            }else{
                emit(DataState.Empty)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }


}