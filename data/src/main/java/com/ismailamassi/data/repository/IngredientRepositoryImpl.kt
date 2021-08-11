package com.ismailamassi.data.repository

import com.ismailamassi.data.db.ingredient.IngredientDao
import com.ismailamassi.domain.repository.IngredientRepository
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val ingredientDao: IngredientDao
) : IngredientRepository {
}