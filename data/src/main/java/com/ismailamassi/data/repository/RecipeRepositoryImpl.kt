package com.ismailamassi.data.repository

import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.ingredient.IngredientDao
import com.ismailamassi.data.db.recipe.RecipeDao
import com.ismailamassi.data.db.step.StepDao
import com.ismailamassi.data.mapper.toDto
import com.ismailamassi.data.mapper.toListDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.repository.RecipeRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao,
    private val stepDao: StepDao
) : RecipeRepository {
    override suspend fun create(recipeDto: RecipeDto): Flow<DataState<Long>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun update(recipeDto: RecipeDto): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun delete(recipeId: Long): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun get(id: Long): Flow<DataState<RecipeDto>> = flow {
        try {
            emit(DataState.Loading)
            val result = recipeDao.get(id)
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

    override suspend fun createList(recipesList: List<RecipeDto>): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun updateList(recipesList: List<RecipeDto>): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun deleteList(recipesIds: List<Long>): Flow<DataState<Int>> = flow {
        try {
            emit(DataState.Loading)
            // TODO: 8/11/2021 SEND TO SERVER IF USER DO ACTION ELSE SYNC TABLE
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun getList(recipesIds: List<Long>): Flow<DataState<List<RecipeDto>>> = flow {
        try {
            emit(DataState.Loading)
            val result = recipeDao.get(recipesIds)
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

    override suspend fun getAll(): Flow<DataState<List<RecipeDto>>> = flow {
        try {
            emit(DataState.Loading)
            val result = recipeDao.get()
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
            val result = recipeDao.count()
            emit(DataState.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    override suspend fun search(query: String): Flow<DataState<List<RecipeDto>>> = flow {
        try {
            emit(DataState.Loading)
            val result = recipeDao.search(query)
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

    override suspend fun getFullRecipe(id: Long): Flow<DataState<RecipeDto>> = flow {
        try {
            emit(DataState.Loading)
            val recipe = recipeDao.get(id)
            if (recipe != DatabaseErrorName.GET_ERROR) {
                val ingredients = ingredientDao.getForRecipe(id)
                val steps = stepDao.getForRecipe(id)
                emit(
                    DataState.Success(
                        recipe.toDto(ingredients = ingredients, steps = steps)
                    )
                )
            } else {
                emit(DataState.Error(Exception(DatabaseErrorName.ERROR_GET)))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }
}