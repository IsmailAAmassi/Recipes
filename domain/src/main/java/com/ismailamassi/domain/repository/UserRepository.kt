package com.ismailamassi.domain.repository

import com.ismailamassi.domain.model.user.AuthenticateRequestDto
import com.ismailamassi.domain.model.user.UserDto
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun create(user: UserDto): Flow<DataState<UserDto>>

    suspend fun update(user: UserDto): Flow<DataState<UserDto>>

    suspend fun initGuestUser(): Flow<DataState<UserDto>>

    suspend fun logout(user: UserDto): Flow<DataState<UserDto>>

    suspend fun login(authenticateRequestDto: AuthenticateRequestDto): Flow<DataState<UserDto>>

}