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
        @Path("id") id: Long
    ): CategoryDto?

    @GET("$CATEGORY_TABLE/{id}")
    suspend fun get(
        @Header("Authorization") token: String,
        @Path("id") id: Long
    ): CategoryDto

    @POST("$CATEGORY_TABLE/list")
    suspend fun createList(
        @Header("Authorization") token: String,
        @Body categoriesList: List<CategoryDto>,
    ): List<CategoryDto>

    @PUT("$CATEGORY_TABLE/list")
    suspend fun updateList(
        @Header("Authorization") token: String,
        @Body categoriesList: List<CategoryDto>,
    ): List<CategoryDto>

    @DELETE("$CATEGORY_TABLE/list")
    suspend fun deleteList(
        @Header("Authorization") token: String,
        @Body categoriesList: List<Long>,
    ): List<CategoryDto>

    @GET(CATEGORY_TABLE)
    suspend fun getAll(
        @Header("Authorization") token: String,
    ): List<CategoryDto>

    @GET("$CATEGORY_TABLE/after/{lastUpdated}")
    suspend fun getAddedUpdated(
        @Header("Authorization") token: String,
        @Path("lastUpdated") lastUpdated: Long,
    ): List<CategoryDto>

    @GET("$CATEGORY_TABLE/before/0")
    suspend fun getDeleted(
        @Header("Authorization") token: String
    ): List<CategoryDto>

    @DELETE(CATEGORY_TABLE)
    suspend fun deleteAll(
        @Header("Authorization") token: String,
    ): List<CategoryDto>
}