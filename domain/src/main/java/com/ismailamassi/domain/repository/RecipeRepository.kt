package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun create(recipeDto: RecipeDto): Flow<DataState<RecipeDto>>

    suspend fun update(
        recipeDto: RecipeDto,
        deletedIngredient: List<Long>,
        deletedSteps: List<Long>
    ): Flow<DataState<RecipeDto>>

    suspend fun delete(recipeId: Long): Flow<DataState<RecipeDto>>

    suspend fun get(id: Long): Flow<DataState<RecipeDto>>


    suspend fun createList(
        recipesList: List<RecipeDto>,
        isUserDoAction: Boolean = false
    ): Flow<DataState<List<Long>>>

    suspend fun updateList(
        recipesList: List<RecipeDto>,
    ): Flow<DataState<Int>>

    suspend fun deleteList(
        recipesIds: List<Long>,
    ): Flow<DataState<Int>>

    suspend fun getList(recipesIds: List<Long>): Flow<DataState<List<RecipeDto>>>


    suspend fun getAll(): Flow<DataState<List<RecipeDto>>>

    suspend fun getAllFromAPI(): Flow<DataState<List<RecipeDto>>>

    suspend fun getCategoryRecipes(categoryId: Long): Flow<DataState<List<RecipeDto>>>


    suspend fun deleteAll(
        isUserDoAction: Boolean = false
    ): Flow<DataState<Int>>

    suspend fun getCount(): Flow<DataState<Int>>

    suspend fun search(query: String): Flow<DataState<List<RecipeDto>>>

    suspend fun getFullRecipe(id: Long): Flow<DataState<RecipeDto>>
}