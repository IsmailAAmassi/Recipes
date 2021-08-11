package com.ismailamassi.domain.model.settings

import android.os.Parcel
import android.os.Parcelable

data class SettingsDto(
    var id: Long?,
    var currentUserId: Long?,
    var currentUserToken: String?,
    var theme: Int?,
    var language: String?,
    var isFirstTime: Boolean?,
    var isLogin: Boolean?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        currentUserId = parcel.readLong(),
        currentUserToken = parcel.readString(),
        theme = parcel.readInt(),
        language = parcel.readString(),
        isFirstTime = parcel.readByte() != 0.toByte(),
        isLogin = parcel.readByte() != 0.toByte(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeLong(currentUserId!!)
        parcel.writeValue(theme)
        parcel.writeString(language)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SettingsDto> {
        override fun createFromParcel(parcel: Parcel): SettingsDto {
            return SettingsDto(parcel)
        }

        override fun newArray(size: Int): Array<SettingsDto?> {
            return arrayOfNulls(size)
        }
    }

}