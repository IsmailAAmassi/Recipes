package com.ismailamassi.data.repository

import com.ismailamassi.data.api.ApiErrorName
import com.ismailamassi.data.api.category.CategoryApi
import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.category.CategoryDao
import com.ismailamassi.data.db.recipe.RecipeDao
import com.ismailamassi.data.mapper.toData
import com.ismailamassi.data.mapper.toDto
import com.ismailamassi.data.mapper.toListData
import com.ismailamassi.data.mapper.toListDto
import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.domain.repository.CategoryRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val categoryApi: CategoryApi,
    private val recipeDao: RecipeDao
) : CategoryRepository {

    override suspend fun create(categoryDto: CategoryDto): Flow<DataState<CategoryDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = categoryApi.create("", categoryDto)
                val dbResult = categoryDao.insert(apiResult.toData())
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

    override suspend fun update(categoryDto: CategoryDto): Flow<DataState<CategoryDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = categoryApi.update("", categoryDto)
                val dbResult = categoryDao.update(apiResult.toData())
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

    override suspend fun delete(categoryId: Long): Flow<DataState<CategoryDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiResult = categoryApi.delete("", categoryId)
                val dbResult = categoryDao.delete(categoryId)
                if (apiResult != null) {
                    if (dbResult != DatabaseErrorName.DELETE_ERROR_CODE) {
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

    override suspend fun get(id: Long): Flow<DataState<CategoryDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val category = categoryDao.get(id)
                if (category != DatabaseErrorName.GET_ERROR) {
                    val recipesCount = recipeDao.countForCategory(id)
                    emit(DataState.Success(category.toDto(recipesCount)))
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.ERROR_GET_MESSAGE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun createList(
        categoriesList: List<CategoryDto>,
        isUserDoAction: Boolean
    ): Flow<DataState<List<Long>>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = categoryApi.createList("", categoriesList)
                    categoriesList.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = categoryDao.insert(categoriesList.toListData())
                    if (dbResult.size == categoriesList.size) {
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
        categoriesList: List<CategoryDto>,
        isUserDoAction: Boolean
    ): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = categoryApi.updateList("", categoriesList)
                    categoriesList.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = categoryDao.update(categoriesList.toListData())
                    if (dbResult == categoriesList.size) {
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
        categoriesIds: List<Long>,
        isUserDoAction: Boolean
    ): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = categoryApi.deleteList("", categoriesIds)
                    categoriesIds.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = categoryDao.delete(categoriesIds)
                    if (dbResult == categoriesIds.size) {
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

    override suspend fun getList(categoriesIds: List<Long>): Flow<DataState<List<CategoryDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val categories = categoryDao.get(categoriesIds)
                if (categories.isNotEmpty()) {
                    val recipesCounts = categories.map {
                        recipeDao.countForCategory(it.id)
                    }
                    emit(DataState.Success(categories.toListDto(recipesCounts)))
                } else {
                    emit(DataState.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getAll(): Flow<DataState<List<CategoryDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val categories = categoryDao.get()
                if (categories.isNotEmpty()) {
                    val recipesCounts = categories.map {
                        recipeDao.countForCategory(it.id)
                    }
                    emit(DataState.Success(categories.toListDto(recipesCounts)))
                } else {
                    emit(DataState.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun deleteAll(isUserDoAction: Boolean): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = categoryApi.deleteAll("")
                    apiResult.isNotEmpty()
                } else true

                if (apiStatus) {
                    val dbResult = categoryDao.drop()
                    val remaining = categoryDao.count()
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
                val count = categoryDao.count()
                emit(DataState.Success(count))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun search(query: String): Flow<DataState<List<CategoryDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val categories = categoryDao.get()
                if (categories.isNotEmpty()) {
                    val recipesCounts = categories.map {
                        recipeDao.countForCategory(it.id)
                    }
                    emit(DataState.Success(categories.toListDto(recipesCounts)))
                } else {
                    emit(DataState.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)
}