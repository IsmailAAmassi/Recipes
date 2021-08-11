package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
    suspend fun create(ingredientDto: IngredientDto): Flow<DataState<Long>>

    suspend fun update(ingredientDto: IngredientDto): Flow<DataState<Int>>

    suspend fun delete(ingredientId: Long): Flow<DataState<Int>>

    suspend fun get(id: Long): Flow<DataState<IngredientDto>>


    suspend fun createList(ingredientsList: List<IngredientDto>): Flow<DataState<Int>>

    suspend fun updateList(ingredientsList: List<IngredientDto>): Flow<DataState<Int>>

    suspend fun deleteList(ingredientsIds: List<Long>): Flow<DataState<Int>>

    suspend fun getList(ingredientsIds: List<Long>): Flow<DataState<List<IngredientDto>>>


    suspend fun getAll(): Flow<DataState<List<IngredientDto>>>

    suspend fun deleteAll(): Flow<DataState<Int>>

    suspend fun getCount(): Flow<DataState<Int>>

    suspend fun search(query:String): Flow<DataState<List<IngredientDto>>>
}