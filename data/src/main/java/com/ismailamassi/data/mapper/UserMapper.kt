package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.user.UserData
import com.ismailamassi.domain.model.user.UserDto

fun UserData.toUserDto() = UserDto(id = id)

fun UserDto.toUserData() = UserData(id = id ?: -1)