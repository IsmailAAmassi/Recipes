package com.ismailamassi.data.repository

import com.ismailamassi.data.api.ApiErrorName
import com.ismailamassi.data.api.ingredient.IngredientApi
import com.ismailamassi.data.api.recipe.RecipeApi
import com.ismailamassi.data.api.step.StepApi
import com.ismailamassi.data.db.DatabaseErrorName
import com.ismailamassi.data.db.favourite.FavouriteDao
import com.ismailamassi.data.db.ingredient.IngredientDao
import com.ismailamassi.data.db.recipe.RecipeDao
import com.ismailamassi.data.db.step.StepDao
import com.ismailamassi.data.mapper.toData
import com.ismailamassi.data.mapper.toDto
import com.ismailamassi.data.mapper.toListData
import com.ismailamassi.data.mapper.toListDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.model.recipe.StepDto
import com.ismailamassi.domain.repository.ConfigRepository
import com.ismailamassi.domain.repository.RecipeRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao,
    private val stepDao: StepDao,
    private val recipeApi: RecipeApi,
    private val ingredientApi: IngredientApi,
    private val stepApi: StepApi,
    private val favouriteDao: FavouriteDao,
    private val configRepository: ConfigRepository
) : RecipeRepository {
    override suspend fun create(recipeDto: RecipeDto): Flow<DataState<RecipeDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val apiRecipeResult = recipeApi.create("", recipeDto)
                val apiIngredientResult =
                    ingredientApi.createList("", recipeDto.ingredients ?: listOf())
                val apiStepResult = stepApi.createList("", recipeDto.steps ?: listOf())

                val dbRecipeResult = recipeDao.insert(recipeDto.toData())
                val dbIngredientResult =
                    ingredientDao.insert((recipeDto.ingredients ?: listOf()).toListData())
                val dbStepResult = stepDao.insert((recipeDto.steps ?: listOf()).toListData())

                if (
                    dbRecipeResult != DatabaseErrorName.INSERT_ERROR_CODE &&
                    dbIngredientResult.size == recipeDto.ingredients?.size &&
                    dbStepResult.size == recipeDto.steps?.size
                ) {
                    emit(
                        DataState.Success(
                            apiRecipeResult.copy(
                                ingredients = apiIngredientResult,
                                steps = apiStepResult
                            )
                        )
                    )
                } else {
                    emit(DataState.Error(Exception("")))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun update(
        recipeDto: RecipeDto,
        deletedIngredient: List<Long>,
        deletedSteps: List<Long>
    ): Flow<DataState<RecipeDto>> =
        flow {
            try {
                emit(DataState.Loading)

                val newIngredients = recipeDto.ingredients?.filter { it.id == -1 } ?: listOf()
                val newSteps = recipeDto.steps?.filter { it.id == -1 } ?: listOf()

                val updatedIngredients = recipeDto.ingredients?.filter { it.id != -1 } ?: listOf()
                val updatedSteps = recipeDto.steps?.filter { it.id != -1 } ?: listOf()

                val apiRecipeResult = recipeApi.update("", recipeDto)
                val dbRecipeResult = recipeDao.update(recipeDto.toData())

                if (newIngredients.isNotEmpty()) {
                    val apiInsertedIngredientResult = ingredientApi.createList("", newIngredients)
                    val dbInsertedIngredientResult =
                        ingredientDao.insert(apiInsertedIngredientResult.toListData())
                }

                if (updatedIngredients.isNotEmpty()) {
                    val apiUpdatedIngredientResult =
                        ingredientApi.updateList("", updatedIngredients)
                    val dbUpdatedIngredientResult =
                        ingredientDao.update(apiUpdatedIngredientResult.toListData())
                }

                if (deletedIngredient.isNotEmpty()) {
                    val apiDeletedIngredientStepsResult =
                        ingredientApi.deleteList("", deletedIngredient)
                    val dbDeletedIngredientStepsResult = ingredientDao.delete(deletedIngredient)
                }

                if (newSteps.isNotEmpty()) {
                    val apiInsertedStepsResult = stepApi.createList("", newSteps)
                    val dbInsertedStepsResult = stepDao.insert(apiInsertedStepsResult.toListData())
                }
                val apiUpdatedStepsResult: List<StepDto>
                if (updatedSteps.isNotEmpty()) {
                    apiUpdatedStepsResult = stepApi.updateList("", updatedSteps)
                    val dbUpdatedStepsResult = stepDao.update(apiUpdatedStepsResult.toListData())
                }

                if (deletedSteps.isNotEmpty()) {
                    val apiDeletedStepsResult = stepApi.deleteList("", deletedSteps)
                    val dbDeletedStepsResult = stepDao.delete(deletedSteps)
                }
                if (dbRecipeResult != DatabaseErrorName.UPDATE_ERROR_CODE) {
                    getFullRecipe(0L).collect {
                        emit(it)
                    }
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.UPDATE_ERROR_MESSAGE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun delete(recipeId: Long): Flow<DataState<RecipeDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val ingredientIds = ingredientDao.getForRecipe(recipeId).map { it.id }
                val stepsIds = stepDao.getForRecipe(recipeId).map { it.id }

                val apiRecipeResult = recipeApi.delete("", recipeId)
                val apiIngredientResult = ingredientApi.deleteList("", ingredientIds)
                val apiStepResult = stepApi.deleteList("", stepsIds)

                val dbRecipeResult = recipeDao.delete(recipeId)
                val dbIngredientResult = ingredientDao.delete(ingredientIds)
                val dbStepResult = stepDao.delete(stepsIds)
                if (
                    dbRecipeResult != DatabaseErrorName.DELETE_ERROR_CODE &&
                    dbIngredientResult != DatabaseErrorName.DELETE_ERROR_CODE &&
                    dbStepResult != DatabaseErrorName.DELETE_ERROR_CODE
                ) {
                    emit(
                        DataState.Success(
                            apiRecipeResult.copy(
                                ingredients = apiIngredientResult,
                                steps = apiStepResult
                            )
                        )
                    )
                } else {
                    emit(DataState.Error(Exception("")))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun get(id: Long): Flow<DataState<RecipeDto>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = recipeDao.get(id)
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
        recipesList: List<RecipeDto>,
        isUserDoAction: Boolean
    ): Flow<DataState<List<Long>>> =
        flow {
            try {
                emit(DataState.Loading)

                // IF USER DO ACTION SEND TO SERVER
                val apiStatus = if (isUserDoAction) {
                    val apiResult = recipeApi.createList("", recipesList)
                    recipesList.size == apiResult.size
                } else true

                if (apiStatus) {
                    val dbResult = recipeDao.insert(recipesList.toListData())
                    if (dbResult.size == recipesList.size) {
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
        recipesList: List<RecipeDto>
    ): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = recipeDao.update(recipesList.toListData())
                if (result == recipesList.size) {
                    emit(DataState.Success(result))
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_UPDATE_ERROR_MESSAGE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun deleteList(
        recipesIds: List<Long>
    ): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = recipeDao.delete(recipesIds)
                if (result == recipesIds.size) {
                    emit(DataState.Success(result))
                } else {
                    emit(DataState.Error(Exception(DatabaseErrorName.MULTIPLE_DELETE_ERROR_MESSAGE)))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getList(recipesIds: List<Long>): Flow<DataState<List<RecipeDto>>> =
        flow {
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
        }.flowOn(Dispatchers.IO)

    override suspend fun getAll(): Flow<DataState<List<RecipeDto>>> =
        flow {
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
        }.flowOn(Dispatchers.IO)


    override suspend fun getCategoryRecipes(categoryId: Long): Flow<DataState<List<RecipeDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                println("getCategoryRecipes")
                val result = recipeDao.getCategoryRecipes(categoryId)
                println("getCategoryRecipes result $result")
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
                    val apiResult = recipeApi.deleteAll("")
                    apiResult.isNotEmpty()
                } else true

                if (apiStatus) {
                    val dbResult = recipeDao.drop()
                    val remaining = recipeDao.count()
                    if (remaining == 0) {
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

    override suspend fun getCount(): Flow<DataState<Int>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = recipeDao.count()
                emit(DataState.Success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun search(query: String): Flow<DataState<List<RecipeDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                delay(1500)
                val result = recipeDao.search("%$query%")
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

    override suspend fun getFullRecipe(id: Long): Flow<DataState<RecipeDto>> =
        flow {
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
                    emit(DataState.Error(Exception(DatabaseErrorName.ERROR_GET_MESSAGE)))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun syncTable(): Flow<Boolean> = flow {
        try {
            val lastUpdate = configRepository.getLastUpdateRecipeTable()
            val addedUpdatedRecipes = recipeApi.getAddedUpdated("", lastUpdate)
            val deletedRecipes = recipeApi.getDeleted("")

            //Update Variable in SP
            configRepository.setLastUpdateRecipeTable(System.currentTimeMillis())

            //Add addedUpdatedRecipes to DB
            recipeDao.insert(addedUpdatedRecipes.toListData())

            //Delete deletedRecipes from DB
            recipeDao.delete(deletedRecipes.map { it.id })
            emit(true)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(false)
        }
    }

    override suspend fun getMostLovedRecipes(): Flow<DataState<List<RecipeDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = recipeApi.getMostLoved("")
                if (result.isNotEmpty()) {
                    emit(DataState.Success(result))
                } else {
                    emit(DataState.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getBestCollectionRecipes(): Flow<DataState<List<RecipeDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = recipeApi.getBestCollection("")
                if (result.isNotEmpty()) {
                    emit(DataState.Success(result))
                } else {
                    emit(DataState.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getMostViewedRecipes(): Flow<DataState<List<RecipeDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = recipeApi.getMostViewed("")
                if (result.isNotEmpty()) {
                    emit(DataState.Success(result))
                } else {
                    emit(DataState.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getFavouritesRecipes(): Flow<DataState<List<RecipeDto>>> =
        flow {
            try {
                emit(DataState.Loading)
                val favouritesResult = favouriteDao.get()
                if (favouritesResult.isNotEmpty()) {
                    val recipesResult = recipeDao.get(favouritesResult.map { it.recipeId })
                    if (recipesResult.isNotEmpty()) {
                        emit(DataState.Success(recipesResult.toListDto()))
                    } else {
                        emit(DataState.Empty)
                    }
                } else {
                    emit(DataState.Empty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)


}