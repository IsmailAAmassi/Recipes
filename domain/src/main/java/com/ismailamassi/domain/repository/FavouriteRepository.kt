package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.favourite.FavouriteDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    suspend fun create(favouriteDto: FavouriteDto): Flow<DataState<Long>>

    suspend fun delete(favouriteDto: FavouriteDto): Flow<DataState<Int>>
}