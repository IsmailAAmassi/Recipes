package com.ismailamassi.domain.model.user

import android.os.Parcel
import android.os.Parcelable

data class UserDto(
    val id: Long?,

) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeLong(id!!)
    }

    companion object CREATOR : Parcelable.Creator<UserDto> {
        override fun createFromParcel(parcel: Parcel): UserDto {
            return UserDto(parcel)
        }

        override fun newArray(size: Int): Array<UserDto?> {
            return arrayOfNulls(size)
        }
    }

}
