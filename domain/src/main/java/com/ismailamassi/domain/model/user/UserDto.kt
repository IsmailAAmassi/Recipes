package com.ismailamassi.domain.model.user

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String?,
    @SerializedName("imageURL") val imageURL: String?,
    @SerializedName("token") val token: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(token)
    }

    override fun describeContents(): Int {
        return 0
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
