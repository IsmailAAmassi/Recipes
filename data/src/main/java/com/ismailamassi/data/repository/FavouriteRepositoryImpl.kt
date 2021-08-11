package com.ismailamassi.data.repository

import com.ismailamassi.data.db.favourite.FavouriteDao
import com.ismailamassi.domain.repository.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteDao: FavouriteDao
) : FavouriteRepository {
}