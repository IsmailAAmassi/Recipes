package com.ismailamassi.data.api.tip

import com.ismailamassi.data.api.ApiTablesNames.TIP_TABLE
import com.ismailamassi.domain.model.recipe.IngredientDto
import retrofit2.http.*

interface TipApi {

    @POST(TIP_TABLE)
    suspend fun create(
        @Header("Authorization") token: String,
        @Body ingredientDto: IngredientDto
    ): IngredientDto

    @PUT(TIP_TABLE)
    suspend fun update(
        @Header("Authorization") token: String,
        @Body ingredientDto: IngredientDto
    ): IngredientDto

    @DELETE("${TIP_TABLE}/{id}")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): IngredientDto

    @GET("${TIP_TABLE}/{id}")
    suspend fun get(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): IngredientDto

    @POST("${TIP_TABLE}/list")
    suspend fun createList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @PUT("${TIP_TABLE}/list")
    suspend fun updateList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @DELETE("${TIP_TABLE}/list")
    suspend fun deleteList(
        @Header("Authorization") token: String,
        @Body goalList: List<IngredientDto>,
    ): List<IngredientDto>

    @GET(TIP_TABLE)
    suspend fun getAll(
        @Header("Authorization") token: String,
    ): List<IngredientDto>
}