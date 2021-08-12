package com.ismailamassi.data.api.step

import com.ismailamassi.data.api.ApiTablesNames.STEP_TABLE
import com.ismailamassi.domain.model.recipe.StepDto
import retrofit2.http.*

interface StepApi {

    @POST(STEP_TABLE)
    suspend fun create(
        @Header("Authorization") token: String,
        @Body stepDto: StepDto
    ): StepDto

    @PUT(STEP_TABLE)
    suspend fun update(
        @Header("Authorization") token: String,
        @Body stepDto: StepDto
    ): StepDto

    @DELETE("${STEP_TABLE}/{id}")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Path("id") id: Long
    ): StepDto?

    @GET("${STEP_TABLE}/{id}")
    suspend fun get(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): StepDto

    @POST("${STEP_TABLE}/list")
    suspend fun createList(
        @Header("Authorization") token: String,
        @Body stepList: List<StepDto>,
    ): List<StepDto>

    @PUT("${STEP_TABLE}/list")
    suspend fun updateList(
        @Header("Authorization") token: String,
        @Body stepList: List<StepDto>,
    ): List<StepDto>

    @DELETE("${STEP_TABLE}/list")
    suspend fun deleteList(
        @Header("Authorization") token: String,
        @Body stepList: List<Long>,
    ): List<StepDto>

    @GET(STEP_TABLE)
    suspend fun getAll(
        @Header("Authorization") token: String,
    ): List<StepDto>

    @DELETE(STEP_TABLE)
    suspend fun deleteAll(
        @Header("Authorization") token: String,
    ): List<StepDto>
}