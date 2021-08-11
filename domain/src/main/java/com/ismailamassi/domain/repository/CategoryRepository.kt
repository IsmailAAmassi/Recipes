package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun create(categoryDto: CategoryDto): Flow<DataState<Long>>

    suspend fun update(categoryDto: CategoryDto): Flow<DataState<Int>>

    suspend fun delete(categoryId: Long): Flow<DataState<Int>>

    suspend fun get(id: Long): Flow<DataState<CategoryDto>>


    suspend fun createList(categoriesList: List<CategoryDto>): Flow<DataState<Int>>

    suspend fun updateList(categoriesList: List<CategoryDto>): Flow<DataState<Int>>

    suspend fun deleteList(categoriesIds: List<String>): Flow<DataState<Int>>

    suspend fun getList(categoriesIds: List<String>): Flow<DataState<List<CategoryDto>>>


    suspend fun getAll(): Flow<DataState<List<CategoryDto>>>

    suspend fun deleteAll(): Flow<DataState<Int>>

    suspend fun getCount(): Flow<DataState<Int>>

    suspend fun search(query:String): Flow<DataState<List<CategoryDto>>>

}