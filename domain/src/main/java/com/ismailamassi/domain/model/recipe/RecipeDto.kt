package com.ismailamassi.domain.model.recipe

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("id")var id: Long,
    @SerializedName("title")var title: String?,
    @SerializedName("publisher_id")var publisherId: Long,
    @SerializedName("featured_image")var featuredImage: String?,
    @SerializedName("video_url")var videoURL: String?,

    @SerializedName("preparation_time")var preparationTime: String?,
    @SerializedName("cooking_time")var cookingTime: String?,
    @SerializedName("difficulty")var serving: String?,

    @SerializedName("position")var position: Int,
    @SerializedName("likes_count")var likeCount: Int,
    @SerializedName("last_update")var lastUpdate: Long,
    @SerializedName("category_id")var categoryId: Long,

    @SerializedName("ingredients") var ingredients: List<IngredientDto>? = listOf(),
    @SerializedName("steps") var steps: List<StepDto>? = listOf()

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.createTypedArrayList(IngredientDto),
        parcel.createTypedArrayList(StepDto)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeLong(publisherId)
        parcel.writeString(featuredImage)
        parcel.writeString(videoURL)
        parcel.writeString(preparationTime)
        parcel.writeString(cookingTime)
        parcel.writeString(serving)
        parcel.writeInt(position)
        parcel.writeInt(likeCount)
        parcel.writeLong(lastUpdate)
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
