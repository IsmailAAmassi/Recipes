package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun create(recipeDto: RecipeDto): Flow<DataState<Long>>

    suspend fun update(recipeDto: RecipeDto): Flow<DataState<Int>>

    suspend fun delete(recipeId: Long): Flow<DataState<Int>>

    suspend fun get(id: Long): Flow<DataState<RecipeDto>>


    suspend fun createList(recipesList: List<RecipeDto>): Flow<DataState<Int>>

    suspend fun updateList(recipesList: List<RecipeDto>): Flow<DataState<Int>>

    suspend fun deleteList(recipesIds: List<Long>): Flow<DataState<Int>>

    suspend fun getList(recipesIds: List<Long>): Flow<DataState<List<RecipeDto>>>


    suspend fun getAll(): Flow<DataState<List<RecipeDto>>>

    suspend fun deleteAll(): Flow<DataState<Int>>

    suspend fun getCount(): Flow<DataState<Int>>

    suspend fun search(query:String): Flow<DataState<List<RecipeDto>>>

    suspend fun getFullRecipe(id: Long): Flow<DataState<RecipeDto>>
}