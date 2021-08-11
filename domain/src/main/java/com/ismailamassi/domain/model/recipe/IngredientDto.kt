package com.ismailamassi.domain.model.recipe

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class IngredientDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") var name: String?,
    @SerializedName("qty") var qty: String?,
    @SerializedName("unit") var unit: String?,
    @SerializedName("recipeId") var recipeId: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        name = parcel.readString(),
        qty = parcel.readString(),
        unit = parcel.readString(),
        recipeId = parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(qty)
        parcel.writeString(unit)
        parcel.writeLong(recipeId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IngredientDto> {
        override fun createFromParcel(parcel: Parcel): IngredientDto {
            return IngredientDto(parcel)
        }

        override fun newArray(size: Int): Array<IngredientDto?> {
            return arrayOfNulls(size)
        }
    }
}
