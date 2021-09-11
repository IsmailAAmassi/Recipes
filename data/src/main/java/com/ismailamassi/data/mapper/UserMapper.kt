package com.ismailamassi.data.mapper

import com.ismailamassi.data.db.user.UserData
import com.ismailamassi.domain.model.user.UserDto

fun UserData.toDto() = UserDto(
    id = id,
    name = name,
    imageURL = imageURL,
    token = token
)

fun UserDto.toData() = UserData(
    id = id,
    name = name ?: "",
    imageURL = imageURL ?: "",
    token = token ?: ""
)