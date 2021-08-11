package com.ismailamassi.data.api.step

import com.ismailamassi.data.api.ApiTablesNames.STEP_TABLE
import com.ismailamassi.domain.model.recipe.IngredientDto
import retrofit2.http.*

interface StepApi {

    @POST(STEP_TABLE)
    suspend fun create(
        @Header("Authorization") token: String,
        @Body ingredientDto: IngredientDto
    ): IngredientDto

    @PUT(STEP_TABLE)
    suspend fun update(
        @Header("Authorization") token: String,
        @Body ingredientDto: IngredientDto
    ): IngredientDto

    @DELETE("${STEP_TABLE}/{id}")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): IngredientDto

    @GET("${STEP_TABLE}/{id}")
    suspend fun get(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): IngredientDto

    @POST("${STEP_TABLE}/list")
    suspend fun createList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @PUT("${STEP_TABLE}/list")
    suspend fun updateList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @DELETE("${STEP_TABLE}/list")
    suspend fun deleteList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @GET(STEP_TABLE)
    suspend fun getAll(
        @Header("Authorization") token: String,
    ): List<IngredientDto>
}