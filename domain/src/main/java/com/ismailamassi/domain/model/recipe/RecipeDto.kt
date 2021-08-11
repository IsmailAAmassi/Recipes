package com.ismailamassi.domain.model.recipe

import android.os.Parcel
import android.os.Parcelable

data class RecipeDto(
    var id: Long,
    var title: String?,
    var publisherId: String?,
    var featuredImage: String?,
    var videoURL: String?,
    var likeCount: Int,
    var categoryId: Long,
    var ingredients: List<IngredientDto>,
    var steps: List<StepDto>,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readLong(),
        TODO("ingredients"),
        TODO("steps")
    )

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<RecipeDto> {
        override fun createFromParcel(parcel: Parcel): RecipeDto {
            return RecipeDto(parcel)
        }

        override fun newArray(size: Int): Array<RecipeDto?> {
            return arrayOfNulls(size)
        }
    }
}
