package com.ismailamassi.domain.model.recipe

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("id")var id: Long,
    @SerializedName("title")var title: String?,
    @SerializedName("publisherId")var publisherId: String?,
    @SerializedName("featuredImage")var featuredImage: String?,
    @SerializedName("videoURL")var videoURL: String?,
    @SerializedName("likeCount")var likeCount: Int,
    @SerializedName("categoryId")var categoryId: Long,
    var ingredients: List<IngredientDto>?,
    var steps: List<StepDto>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        title = parcel.readString(),
        publisherId = parcel.readString(),
        featuredImage = parcel.readString(),
        videoURL = parcel.readString(),
        likeCount = parcel.readInt(),
        categoryId = parcel.readLong(),
        ingredients = parcel.createTypedArrayList(IngredientDto)?.toList() ?: listOf(),
        steps = parcel.createTypedArrayList(StepDto)?.toList() ?: listOf()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(publisherId)
        parcel.writeString(featuredImage)
        parcel.writeString(videoURL)
        parcel.writeInt(likeCount)
        parcel.writeLong(categoryId)
        parcel.writeTypedList(ingredients)
        parcel.writeTypedList(steps)
    }

    override fun describeContents(): Int {
        return 0
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
