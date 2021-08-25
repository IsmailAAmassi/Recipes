package com.ismailamassi.domain.model.recipe

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class StepDto(
    @SerializedName("id") var id: Long,
    @SerializedName("order") var order: Int,
    @SerializedName("description") var description: String?,
    @SerializedName("recipe_id") var recipeId: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        order = parcel.readInt(),
        description = parcel.readString(),
        recipeId = parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeInt(order)
        parcel.writeString(description)
        parcel.writeLong(recipeId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StepDto> {
        override fun createFromParcel(parcel: Parcel): StepDto {
            return StepDto(parcel)
        }

        override fun newArray(size: Int): Array<StepDto?> {
            return arrayOfNulls(size)
        }
    }
}