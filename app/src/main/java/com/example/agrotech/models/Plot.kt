package com.example.agrotech.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Plot (

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    var name: String?=null,
    @SerializedName("description")
    var description: String?=null,
    @SerializedName("location")
    var location: String?=null,
    @SerializedName("area")
    var area: Float?=null,
    @SerializedName("volume")
    var volume: Float?=null,
    @SerializedName("plotImage")
    var plotImage: String?=null,
    @SerializedName("userId")
    var userId: Int?=null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(location)
        parcel.writeValue(area)
        parcel.writeValue(volume)
        parcel.writeString(plotImage)
        parcel.writeValue(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plot> {
        override fun createFromParcel(parcel: Parcel): Plot {
            return Plot(parcel)
        }

        override fun newArray(size: Int): Array<Plot?> {
            return arrayOfNulls(size)
        }
    }

}

