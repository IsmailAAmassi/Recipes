package com.ismailamassi.data.repository

import com.ismailamassi.data.db.ingredient.IngredientDao
import com.ismailamassi.data.db.recipe.RecipeDao
import com.ismailamassi.data.db.step.StepDao
import com.ismailamassi.data.mapper.toDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.repository.IngredientRepository
import com.ismailamassi.domain.repository.RecipeRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao,
    private val stepDao: StepDao,
    private val ingredientRepository: IngredientRepository
) : RecipeRepository {

    override suspend fun getFullRecipe(id: Long): Flow<DataState<RecipeDto>> = flow {
        try {
            emit(DataState.Loading)
            val recipe = recipeDao.get(id)
            val ingredients = ingredientDao.getForRecipe(id)
            val steps = stepDao.getForRecipe(id)
            emit(
                DataState.Success(
                    recipe.toDto(ingredients = ingredients, steps = steps)
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }
}