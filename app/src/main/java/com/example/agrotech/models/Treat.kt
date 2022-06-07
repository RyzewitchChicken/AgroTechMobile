package com.example.agrotech.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Treat {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("type")
    var type: String?=null
    @SerializedName("description")
    var description: String?=null
    @SerializedName("certificate")
    var certificate: String?=null
}