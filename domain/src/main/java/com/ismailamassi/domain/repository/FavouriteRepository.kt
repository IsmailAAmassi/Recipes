package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.favourite.FavouriteDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    suspend fun create(favouriteDto: FavouriteDto): Flow<DataState<Long>>

    suspend fun delete(id: Long): Flow<DataState<Int>>

    suspend fun getAll(): Flow<DataState<List<FavouriteDto>>>

    suspend fun isRecipeFavourite(recipeId:Long): Flow<DataState<Boolean>>

}