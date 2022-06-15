package com.example.agrotech.models

import com.google.gson.annotations.SerializedName
import java.util.*

class Season {

    @SerializedName("id")
    var id: Int?=null

    @SerializedName("description")
    var description: String?=null

    @SerializedName("startDate")
    var startDate: Date?=null

    @SerializedName("endDate")
    var endDate: Date?=null
}