package com.example.agrotech.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Chilli {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    var name: String?=null

    @SerializedName("description")
    var description: String?=null


}