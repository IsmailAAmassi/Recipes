package com.ismailamassi.data.repository

import com.ismailamassi.data.db.user.UserDao
import com.ismailamassi.data.db.user.UserData
import com.ismailamassi.data.mapper.toUserDto
import com.ismailamassi.domain.model.user.AuthenticateRequestDto
import com.ismailamassi.domain.model.user.UserDto
import com.ismailamassi.domain.repository.SettingsRepository
import com.ismailamassi.domain.repository.UserRepository
import com.ismailamassi.domain.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val settingsRepository: SettingsRepository
) : UserRepository {

    override suspend fun create(user: UserDto): Flow<DataState<UserDto>> =
        flow {
            emit(DataState.Loading)
            delay(500)
            try {

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun update(user: UserDto): Flow<DataState<UserDto>> =
        flow {
            emit(DataState.Loading)
            delay(500)
            try {

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun logout(user: UserDto): Flow<DataState<UserDto>> =
        flow {
            emit(DataState.Loading)
            delay(500)
            try {

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun initGuestUser(): Flow<DataState<UserDto>> =
        flow {
            emit(DataState.Loading)
            delay(500)
            try {
                when (userDao.getCount()) {
                    0 -> {
                        val guestUser = UserData(id = 0)
                        userDao.insert(guestUser)
                        emit(DataState.Success(guestUser.toUserDto()))
                    }
                    else -> {
                        val currentUser = userDao.getCurrentUser()
                        if (currentUser != null) emit(DataState.Success(currentUser.toUserDto()))
                        else emit(DataState.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun login(authenticateRequestDto: AuthenticateRequestDto): Flow<DataState<UserDto>> =
        flow {
            emit(DataState.Loading)
            try {

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }.flowOn(Dispatchers.IO)
}