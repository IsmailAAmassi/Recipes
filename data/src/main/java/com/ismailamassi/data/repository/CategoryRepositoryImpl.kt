package com.ismailamassi.data.repository

import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.category.CategoryDao
import com.ismailamassi.data.db.recipe.RecipeDao
import com.ismailamassi.data.mapper.toDto
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
    private val recipeDao: RecipeDao
) : CategoryRepository {

    override suspend fun create(categoryDto: CategoryDto): Flow<DataState<Long>> =
        flow {
            try {
                emit(DataState.Loading)
                // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun update(categoryDto: CategoryDto): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun delete(categoryId: Long): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
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
                    emit(DataState.Error(Exception(DatabaseErrorName.ERROR_GET)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun createList(categoriesList: List<CategoryDto>): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun updateList(categoriesList: List<CategoryDto>): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun deleteList(categoriesIds: List<Long>): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
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