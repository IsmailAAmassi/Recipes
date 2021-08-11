package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.favourite.FavouriteData
import com.ismailamassi.domain.model.favourite.FavouriteDto


fun FavouriteDto.toData() = FavouriteData(recipeId = recipeId)

fun FavouriteData.toDto() = FavouriteDto(recipeId = recipeId)