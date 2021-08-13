package com.ismailamassi.data.repository

import com.ismailamassi.data.api.ApiErrorName
import com.ismailamassi.data.api.ingredient.IngredientApi
import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.ingredient.IngredientDao
import com.ismailamassi.data.mapper.toData
import com.ismailamassi.data.mapper.toDto
import com.ismailamassi.data.mapper.toListData
import com.ismailamassi.data.mapper.toListDto
import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.domain.repository.IngredientRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val ingredientDao: IngredientDao,
    private val ingredientApi: IngredientApi
) : IngredientRepository {

    override suspend fun create(ingredientDto: IngredientDto): Flow<DataState<IngredientDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = ingredientApi.create("", ingredientDto)
                val dbResult = ingredientDao.insert(ingredientDto.toData())
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

    override suspend fun update(ingredientDto: IngredientDto): Flow<DataState<IngredientDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = ingredientApi.update("", ingredientDto)
                val dbResult = ingredientDao.update(ingredientDto.toData())
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

    override suspend fun delete(ingredientId: Long): Flow<DataState<IngredientDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = ingredientApi.delete("", ingredientId)
                val dbResult = ingredientDao.delete(ingredientId)
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

    override suspend fun get(id: Long): Flow<DataState<IngredientDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = ingredientDao.get(id)
                if (result != null) {
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
        ingredientsList: List<IngredientDto>,
        isUserDoAction: Boolean
    ): Flow<DataState<List<Long>>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = ingredientApi.createList("", ingredientsList)
                    ingredientsList.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = ingredientDao.insert(ingredientsList.toListData())
                    if (dbResult.size == ingredientsList.size) {
                        emit(DataState.Success(dbResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_INSERT_ERROR_MESSAGE)))
                    }
                }else{
                    emit(DataState.Error(Exception(ApiErrorName.MULTIPLE_ERROR_INSERT)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun updateList(
        ingredientsList: List<IngredientDto>,
        isUserDoAction: Boolean
    ): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = ingredientApi.updateList("", ingredientsList)
                    ingredientsList.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = ingredientDao.update(ingredientsList.toListData())
                    if (dbResult == ingredientsList.size) {
                        emit(DataState.Success(dbResult))
                    } else {
                        emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_UPDATE_ERROR_MESSAGE)))
                    }
                }else{
                    emit(DataState.Error(Exception(ApiErrorName.MULTIPLE_ERROR_UPDATE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun deleteList(
        ingredientsIds: List<Long>,
        isUserDoAction: Boolean
    ): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = ingredientApi.deleteList("", ingredientsIds)
                    ingredientsIds.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = ingredientDao.delete(ingredientsIds)
                    if (dbResult == ingredientsIds.size) {
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

    override suspend fun getList(ingredientsIds: List<Long>): Flow<DataState<List<IngredientDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = ingredientDao.get(ingredientsIds)
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

    override suspend fun getAll(): Flow<DataState<List<IngredientDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = ingredientDao.get()
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
                    val apiResult = ingredientApi.deleteAll("")
                    apiResult.isNotEmpty()
                } else true

                if (apiStatus) {
                    val dbResult = ingredientDao.drop()
                    val remaining = ingredientDao.count()
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
                val result = ingredientDao.count()
                emit(DataState.Success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun search(query: String): Flow<DataState<List<IngredientDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = ingredientDao.search(query)
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