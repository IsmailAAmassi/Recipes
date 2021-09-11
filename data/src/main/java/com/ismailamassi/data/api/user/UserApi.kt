package com.ismailamassi.data.api.user

import com.ismailamassi.data.api.ApiTablesNames.USER_TABLE
import com.ismailamassi.domain.model.user.AuthenticateRequestDto
import com.ismailamassi.domain.model.user.UserDto
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("$USER_TABLE/login")
    suspend fun login(
        @Body authenticateRequestDto: AuthenticateRequestDto
    ): UserDto?
}