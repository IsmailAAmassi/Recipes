package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.favourite.FavouriteData
import com.ismailamassi.domain.model.favourite.FavouriteDto


fun FavouriteDto.toData() = FavouriteData(recipeId = recipeId, userId = userId)

fun FavouriteData.toDto() = FavouriteDto(recipeId = recipeId, userId = userId)

fun List<FavouriteDto>.toListData() = this.map{it.toData()}

fun List<FavouriteData>.toListDto() = this.map{it.toDto()}
