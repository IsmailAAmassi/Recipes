package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.domain.model.recipe.StepDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface StepRepository {

    suspend fun create(stepDto: StepDto): Flow<DataState<StepDto>>

    suspend fun update(stepDto: StepDto): Flow<DataState<StepDto>>

    suspend fun delete(stepId: Long): Flow<DataState<StepDto>>

    suspend fun get(id: Long): Flow<DataState<StepDto>>


    suspend fun createList(
        stepsList: List<StepDto>,
        isUserDoAction: Boolean = false
    ): Flow<DataState<List<Long>>>

    suspend fun updateList(
        stepsList: List<StepDto>,
        isUserDoAction: Boolean = false
    ): Flow<DataState<Int>>

    suspend fun deleteList(
        stepsIds: List<Long>,
        isUserDoAction: Boolean = false
    ): Flow<DataState<Int>>

    suspend fun getList(stepsIds: List<Long>): Flow<DataState<List<StepDto>>>


    suspend fun getAll(): Flow<DataState<List<StepDto>>>

    suspend fun syncTable(): Flow<Boolean>

    suspend fun deleteAll(
        isUserDoAction: Boolean = false
    ): Flow<DataState<Int>>

    suspend fun getCount(): Flow<DataState<Int>>

    suspend fun search(query: String): Flow<DataState<List<StepDto>>>

}