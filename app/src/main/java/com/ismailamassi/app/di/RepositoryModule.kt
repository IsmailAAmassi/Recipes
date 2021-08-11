package com.ismailamassi.app.di

import com.ismailamassi.data.repository.*
import com.ismailamassi.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository


    @Binds
    abstract fun bindFavouriteRepository(favouriteRepositoryImpl: FavouriteRepositoryImpl): FavouriteRepository


    @Binds
    abstract fun bindIngredientRepository(ingredientRepositoryImpl: IngredientRepositoryImpl): IngredientRepository


    @Binds
    abstract fun bindRecipeRepository(recipeRepositoryImpl: RecipeRepositoryImpl): RecipeRepository


    @Binds
    abstract fun bindStepRepository(stepRepositoryImpl: StepRepositoryImpl): StepRepository


    @Binds
    abstract fun bindTipRepository(tipRepositoryImpl: TipRepositoryImpl): TipRepository

    @Binds
    abstract fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
