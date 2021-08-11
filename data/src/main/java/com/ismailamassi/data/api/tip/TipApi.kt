package com.ismailamassi.data.api.tip

import com.ismailamassi.data.api.ApiTablesNames.TIP_TABLE
import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.domain.model.tip.TipDto
import retrofit2.http.*

interface TipApi {

    @POST(TIP_TABLE)
    suspend fun create(
        @Header("Authorization") token: String,
        @Body tipDto: TipDto
    ): TipDto

    @PUT(TIP_TABLE)
    suspend fun update(
        @Header("Authorization") token: String,
        @Body tipDto: TipDto
    ): TipDto

    @DELETE("${TIP_TABLE}/{id}")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Path("id") id: Long
    ): TipDto?

    @GET("${TIP_TABLE}/{id}")
    suspend fun get(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): TipDto

    @POST("${TIP_TABLE}/list")
    suspend fun createList(
        @Header("Authorization") token: String,
        @Body tipsList: List<TipDto>,
    ): List<TipDto>

    @PUT("${TIP_TABLE}/list")
    suspend fun updateList(
        @Header("Authorization") token: String,
        @Body tipsList: List<TipDto>,
    ): List<TipDto>

    @DELETE("${TIP_TABLE}/list")
    suspend fun deleteList(
        @Header("Authorization") token: String,
        @Body tipsList: List<Long>,
    ): List<TipDto>

    @GET(TIP_TABLE)
    suspend fun getAll(
        @Header("Authorization") token: String,
    ): List<TipDto>

    @DELETE(TIP_TABLE)
    suspend fun deleteAll(
        @Header("Authorization") token: String,
    ): List<TipDto>
}