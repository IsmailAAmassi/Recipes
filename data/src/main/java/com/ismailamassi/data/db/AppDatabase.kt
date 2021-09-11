package com.ismailamassi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ismailamassi.data.db.category.CategoryDao
import com.ismailamassi.data.db.category.CategoryData
import com.ismailamassi.data.db.favourite.FavouriteDao
import com.ismailamassi.data.db.favourite.FavouriteData
import com.ismailamassi.data.db.ingredient.IngredientDao
import com.ismailamassi.data.db.ingredient.IngredientData
import com.ismailamassi.data.db.recipe.RecipeDao
import com.ismailamassi.data.db.recipe.RecipeData
import com.ismailamassi.data.db.step.StepDao
import com.ismailamassi.data.db.step.StepData
import com.ismailamassi.data.db.tip.TipDao
import com.ismailamassi.data.db.tip.TipData
import com.ismailamassi.data.db.user.UserDao
import com.ismailamassi.data.db.user.UserData

@Database(
    entities = [
        CategoryData::class,
        FavouriteData::class,
        IngredientData::class,
        RecipeData::class,
        StepData::class,
        TipData::class,
        UserData::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getFavouriteDao(): FavouriteDao
    abstract fun getIngredientDao(): IngredientDao
    abstract fun getRecipeDao(): RecipeDao
    abstract fun getStepDao(): StepDao
    abstract fun getTipDao(): TipDao
    abstract fun getUserDao(): UserDao
}