package com.ismailamassi.data.repository

import com.ismailamassi.data.db.ingredient.IngredientDao
import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.domain.repository.IngredientRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val ingredientDao: IngredientDao
) : IngredientRepository {
    override suspend fun create(ingredientDto: IngredientDto): Flow<DataState<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun update(ingredientDto: IngredientDto): Flow<DataState<Int>> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(ingredientId: Long): Flow<DataState<Int>> {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: Long): Flow<DataState<IngredientDto>> {
        TODO("Not yet implemented")
    }

    override suspend fun createList(ingredientsList: List<IngredientDto>): Flow<DataState<Int>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateList(ingredientsList: List<IngredientDto>): Flow<DataState<Int>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteList(ingredientsIds: List<Long>): Flow<DataState<Int>> {
        TODO("Not yet implemented")
    }

    override suspend fun getList(ingredientsIds: List<Long>): Flow<DataState<List<IngredientDto>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): Flow<DataState<List<IngredientDto>>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(): Flow<DataState<Int>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCount(): Flow<DataState<Int>> {
        TODO("Not yet implemented")
    }

    override suspend fun search(query: String): Flow<DataState<List<IngredientDto>>> {
        TODO("Not yet implemented")
    }


}