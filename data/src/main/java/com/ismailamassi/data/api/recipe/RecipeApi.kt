package com.ismailamassi.data.api.recipe

import com.ismailamassi.data.api.ApiTablesNames.RECIPE_TABLE
import com.ismailamassi.domain.model.recipe.IngredientDto
import retrofit2.http.*

interface RecipeApi {

    @POST(RECIPE_TABLE)
    suspend fun create(
        @Header("Authorization") token: String,
        @Body ingredientDto: IngredientDto
    ): IngredientDto

    @PUT(RECIPE_TABLE)
    suspend fun update(
        @Header("Authorization") token: String,
        @Body ingredientDto: IngredientDto
    ): IngredientDto

    @DELETE("${RECIPE_TABLE}/{id}")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): IngredientDto

    @GET("${RECIPE_TABLE}/{id}")
    suspend fun get(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): IngredientDto

    @POST("${RECIPE_TABLE}/list")
    suspend fun createList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @PUT("${RECIPE_TABLE}/list")
    suspend fun updateList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @DELETE("${RECIPE_TABLE}/list")
    suspend fun deleteList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @GET(RECIPE_TABLE)
    suspend fun getAll(
        @Header("Authorization") token: String,
    ): List<IngredientDto>
}