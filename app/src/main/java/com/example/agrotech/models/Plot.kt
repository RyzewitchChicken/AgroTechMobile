package com.example.agrotech.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Plot {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    var name: String?=null
    @SerializedName("description")
    var description: String?=null
    @SerializedName("location")
    var location: String?=null
    @SerializedName("area")
    var area: Float?=null
    @SerializedName("volume")
    var volume: Float?=null
    @SerializedName("plotImage")
    var plotImage: String?=null

}