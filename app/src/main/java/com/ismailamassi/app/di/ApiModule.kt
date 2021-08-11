package com.ismailamassi.app.di

import com.ismailamassi.data.api.category.CategoryApi
import com.ismailamassi.data.api.ingredient.IngredientApi
import com.ismailamassi.data.api.recipe.RecipeApi
import com.ismailamassi.data.api.step.StepApi
import com.ismailamassi.data.api.tip.TipApi
import com.ismailamassi.data.api.user.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideCategoryApi(retrofit: Retrofit.Builder): CategoryApi =
        retrofit.build().create(CategoryApi::class.java)


    @Provides
    fun provideIngredientApi(retrofit: Retrofit.Builder): IngredientApi =
        retrofit.build().create(IngredientApi::class.java)


    @Provides
    fun provideRecipeApi(retrofit: Retrofit.Builder): RecipeApi =
        retrofit.build().create(RecipeApi::class.java)


    @Provides
    fun provideStepApi(retrofit: Retrofit.Builder): StepApi =
        retrofit.build().create(StepApi::class.java)


    @Provides
    fun provideTipApi(retrofit: Retrofit.Builder): TipApi =
        retrofit.build().create(TipApi::class.java)


    @Provides
    fun provideUserApi(retrofit: Retrofit.Builder): UserApi =
        retrofit.build().create(UserApi::class.java)
}
