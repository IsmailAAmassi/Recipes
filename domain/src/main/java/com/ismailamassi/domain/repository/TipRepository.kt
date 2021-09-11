package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.tip.TipDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface TipRepository {
    suspend fun create(tipDto: TipDto): Flow<DataState<TipDto>>

    suspend fun update(tipDto: TipDto): Flow<DataState<TipDto>>

    suspend fun delete(tipId: Long): Flow<DataState<TipDto>>

    suspend fun get(id: Long): Flow<DataState<TipDto>>


    suspend fun createList(
        tipsList: List<TipDto>,
        isUserDoAction: Boolean = false
    ): Flow<DataState<List<Long>>>

    suspend fun updateList(
        tipsList: List<TipDto>,
        isUserDoAction: Boolean = false
    ): Flow<DataState<Int>>

    suspend fun deleteList(
        tipsIds: List<Long>,
        isUserDoAction: Boolean = false
    ): Flow<DataState<Int>>

    suspend fun getList(tipsIds: List<Long>): Flow<DataState<List<TipDto>>>


    suspend fun getAll(): Flow<DataState<List<TipDto>>>

    suspend fun syncTable(): Flow<Boolean>

    suspend fun deleteAll(isUserDoAction: Boolean = false): Flow<DataState<Int>>

    suspend fun getCount(): Flow<DataState<Int>>

    suspend fun search(query: String): Flow<DataState<List<TipDto>>>

    suspend fun getRandomTip(): Flow<DataState<TipDto>>

}