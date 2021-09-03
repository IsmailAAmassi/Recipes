package com.ismailamassi.data.api.recipe

import com.ismailamassi.data.api.ApiTablesNames
import com.ismailamassi.data.api.ApiTablesNames.RECIPE_TABLE
import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import retrofit2.http.*

interface RecipeApi {

    @POST(RECIPE_TABLE)
    suspend fun create(
        @Header("Authorization") token: String,
        @Body recipeDto: RecipeDto
    ): RecipeDto

    @PUT(RECIPE_TABLE)
    suspend fun update(
        @Header("Authorization") token: String,
        @Body recipeDto: RecipeDto
    ): RecipeDto

    @DELETE("${RECIPE_TABLE}/{id}")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Path("id") id: Long
    ): RecipeDto

    @GET("${RECIPE_TABLE}/{id}")
    suspend fun get(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): RecipeDto

    @POST("${RECIPE_TABLE}/list")
    suspend fun createList(
        @Header("Authorization") token: String,
        @Body recipesList: List<RecipeDto>,
    ): List<RecipeDto>

    @PUT("${RECIPE_TABLE}/list")
    suspend fun updateList(
        @Header("Authorization") token: String,
        @Body recipesList: List<RecipeDto>,
    ): List<RecipeDto>

    @DELETE("${RECIPE_TABLE}/list")
    suspend fun deleteList(
        @Header("Authorization") token: String,
        @Body recipesList: List<RecipeDto>,
    ): List<RecipeDto>

    @GET(RECIPE_TABLE)
    suspend fun getAll(
        @Header("Authorization") token: String,
    ): List<RecipeDto>

    @GET("${RECIPE_TABLE}/after/{lastUpdated}")
    suspend fun getAddedUpdated(
        @Header("Authorization") token: String,
        @Path("lastUpdated") lastUpdated: Long,
    ): List<RecipeDto>

    @GET("${RECIPE_TABLE}/before/0")
    suspend fun getDeleted(
        @Header("Authorization") token: String
    ): List<RecipeDto>

    @DELETE(RECIPE_TABLE)
    suspend fun deleteAll(
        @Header("Authorization") token: String,
    ): List<RecipeDto>

    @GET("${RECIPE_TABLE}/mostLoved")
    suspend fun getMostLoved(
        @Header("Authorization") token: String,
    ) :List<RecipeDto>

    @GET("${RECIPE_TABLE}/bestCollection")
    suspend fun getBestCollection(
        @Header("Authorization") token: String,
    ) :List<RecipeDto>

    @GET("${RECIPE_TABLE}/mostViewed")
    suspend fun getMostViewed(
        @Header("Authorization") token: String,
    ) :List<RecipeDto>
}