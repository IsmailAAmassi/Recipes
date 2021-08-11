package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun create(categoryDto: CategoryDto): Flow<DataState<CategoryDto>>

    suspend fun update(categoryDto: CategoryDto): Flow<DataState<CategoryDto>>

    suspend fun delete(categoryId: Long): Flow<DataState<CategoryDto>>

    suspend fun get(id: Long): Flow<DataState<CategoryDto>>


    suspend fun createList(
        categoriesList: List<CategoryDto>,
        isUserDoAction: Boolean = false
    ): Flow<DataState<List<Long>>>

    suspend fun updateList(
        categoriesList: List<CategoryDto>,
        isUserDoAction: Boolean = false
    ): Flow<DataState<Int>>

    suspend fun deleteList(
        categoriesIds: List<Long>,
        isUserDoAction: Boolean = false
    ): Flow<DataState<Int>>

    suspend fun getList(categoriesIds: List<Long>): Flow<DataState<List<CategoryDto>>>


    suspend fun getAll(): Flow<DataState<List<CategoryDto>>>

    suspend fun deleteAll(
        isUserDoAction: Boolean = false
    ): Flow<DataState<Int>>

    suspend fun getCount(): Flow<DataState<Int>>

    suspend fun search(query: String): Flow<DataState<List<CategoryDto>>>

}