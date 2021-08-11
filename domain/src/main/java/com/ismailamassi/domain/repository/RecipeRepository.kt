package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getFullRecipe(id: Long): Flow<DataState<RecipeDto>>
}