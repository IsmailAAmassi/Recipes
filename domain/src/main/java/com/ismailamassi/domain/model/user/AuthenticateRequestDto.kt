package com.ismailamassi.domain.model.user

import android.os.Parcel
import android.os.Parcelable

data class AuthenticateRequestDto(
    var password: String? = "",
    var email: String? = "",
) : Parcelable {
    constructor(parcel: Parcel) : this(
        password = parcel.readString(),
        email = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(password)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AuthenticateRequestDto> {
        override fun createFromParcel(parcel: Parcel): AuthenticateRequestDto {
            return AuthenticateRequestDto(parcel)
        }

        override fun newArray(size: Int): Array<AuthenticateRequestDto?> {
            return arrayOfNulls(size)
        }
    }

}