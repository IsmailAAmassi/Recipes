package com.ismailamassi.domain.model.category

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id") var id: Long,
    @SerializedName("title") var title: String?,
    @SerializedName("avatar") var avatar: String?,
    @SerializedName("recipesCount") var recipesCount: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        title = parcel.readString(),
        avatar = parcel.readString(),
        recipesCount = parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(avatar)
        parcel.writeInt(recipesCount ?: 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryDto> {
        override fun createFromParcel(parcel: Parcel): CategoryDto {
            return CategoryDto(parcel)
        }

        override fun newArray(size: Int): Array<CategoryDto?> {
            return arrayOfNulls(size)
        }
    }
}