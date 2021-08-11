package com.ismailamassi.data.api.ingredient

import com.ismailamassi.data.api.ApiTablesNames.INGREDIENT_TABLE
import com.ismailamassi.domain.model.recipe.IngredientDto
import retrofit2.http.*

interface IngredientApi {

    @POST(INGREDIENT_TABLE)
    suspend fun create(
        @Header("Authorization") token: String,
        @Body ingredientDto: IngredientDto
    ): IngredientDto

    @PUT(INGREDIENT_TABLE)
    suspend fun update(
        @Header("Authorization") token: String,
        @Body ingredientDto: IngredientDto
    ): IngredientDto

    @DELETE("${INGREDIENT_TABLE}/{id}")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): IngredientDto

    @GET("${INGREDIENT_TABLE}/{id}")
    suspend fun get(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): IngredientDto

    @POST("${INGREDIENT_TABLE}/list")
    suspend fun createList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @PUT("${INGREDIENT_TABLE}/list")
    suspend fun updateList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @DELETE("${INGREDIENT_TABLE}/list")
    suspend fun deleteList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @GET(INGREDIENT_TABLE)
    suspend fun getAll(
        @Header("Authorization") token: String,
    ): List<IngredientDto>
}