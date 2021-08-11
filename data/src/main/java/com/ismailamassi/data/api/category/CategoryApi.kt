package com.ismailamassi.data.api.category

import com.ismailamassi.data.api.ApiTablesNames.CATEGORY_TABLE
import com.ismailamassi.domain.model.category.CategoryDto
import retrofit2.http.*

interface CategoryApi {

    @POST(CATEGORY_TABLE)
    suspend fun create(
        @Header("Authorization") token: String,
        @Body categoryDto: CategoryDto
    ): CategoryDto

    @PUT(CATEGORY_TABLE)
    suspend fun update(
        @Header("Authorization") token: String,
        @Body categoryDto: CategoryDto
    ): CategoryDto

    @DELETE("$CATEGORY_TABLE/{id}")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): CategoryDto

    @GET("$CATEGORY_TABLE/{id}")
    suspend fun get(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): CategoryDto

    @POST("$CATEGORY_TABLE/list")
    suspend fun createList(
        @Header("Authorization") token: String,
        @Body goalList: List<CategoryDto>,
    ): List<CategoryDto>

    @PUT("$CATEGORY_TABLE/list")
    suspend fun updateList(
        @Header("Authorization") token: String,
        @Body goalList: List<CategoryDto>,
    ): List<CategoryDto>

    @DELETE("$CATEGORY_TABLE/list")
    suspend fun deleteList(
        @Header("Authorization") token: String,
        @Body goalList: List<CategoryDto>,
    ): List<CategoryDto>

    @GET(CATEGORY_TABLE)
    suspend fun getAll(
        @Header("Authorization") token: String,
    ): List<CategoryDto>
}