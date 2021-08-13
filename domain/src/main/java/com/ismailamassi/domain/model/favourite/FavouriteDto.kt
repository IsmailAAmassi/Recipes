package com.ismailamassi.domain.model.favourite

import android.os.Parcel
import android.os.Parcelable

data class FavouriteDto(var recipeId: Long, var userId: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
        recipeId = parcel.readLong(),
        userId = parcel.readLong()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(recipeId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FavouriteDto> {
        override fun createFromParcel(parcel: Parcel): FavouriteDto {
            return FavouriteDto(parcel)
        }

        override fun newArray(size: Int): Array<FavouriteDto?> {
            return arrayOfNulls(size)
        }
    }
}