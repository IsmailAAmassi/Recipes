package com.ismailamassi.domain.model.tip

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TipDto(
    @SerializedName("id") var id: Long,
    @SerializedName("title") var label: String?,
    @SerializedName("last_update") var lastUpdate: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        label = parcel.readString(),
        lastUpdate = parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(label)
        parcel.writeLong(lastUpdate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TipDto> {
        override fun createFromParcel(parcel: Parcel): TipDto {
            return TipDto(parcel)
        }

        override fun newArray(size: Int): Array<TipDto?> {
            return arrayOfNulls(size)
        }
    }
}
