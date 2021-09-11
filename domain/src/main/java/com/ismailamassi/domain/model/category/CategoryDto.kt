package com.ismailamassi.domain.model.category

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id") var id: Long,
    @SerializedName("title") var title: String?,
    @SerializedName("avatar") var avatar: String?,
    @SerializedName("last_update") var lastUpdate: Long,
    @SerializedName("position") var position: Int,
    @SerializedName("recipesCount") var recipesCount: Int? = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(avatar)
        parcel.writeLong(lastUpdate)
        parcel.writeInt(position)
        parcel.writeValue(recipesCount)
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