package com.ismailamassi.app.di

import com.ismailamassi.data.db.AppDatabase
import com.ismailamassi.data.db.category.CategoryDao
import com.ismailamassi.data.db.favourite.FavouriteDao
import com.ismailamassi.data.db.ingredient.IngredientDao
import com.ismailamassi.data.db.recipe.RecipeDao
import com.ismailamassi.data.db.step.StepDao
import com.ismailamassi.data.db.tip.TipDao
import com.ismailamassi.data.db.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao = appDatabase.getCategoryDao()

    @Provides
    fun provideFavouriteDao(appDatabase: AppDatabase): FavouriteDao = appDatabase.getFavouriteDao()

    @Provides
    fun provideIngredientDao(appDatabase: AppDatabase): IngredientDao = appDatabase.getIngredientDao()

    @Provides
    fun provideRecipeDao(appDatabase: AppDatabase): RecipeDao = appDatabase.getRecipeDao()


    @Provides
    fun provideStepDao(appDatabase: AppDatabase): StepDao = appDatabase.getStepDao()

    @Provides
    fun provideTipDao(appDatabase: AppDatabase): TipDao = appDatabase.getTipDao()

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.getUserDao()
}
