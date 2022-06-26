package com.example.agrotech.models

import com.google.gson.annotations.SerializedName
import java.util.*

class PlotPlagues {

    @SerializedName("startDate")
    var startDate: Date?=null

    @SerializedName("endDate")
    var endDate: Date?=null

    @SerializedName("damage")
    var damage: String?=null

    @SerializedName("plotId")
    var plotId: Int?=null
    @SerializedName("plagueId")
    var plagueId: Int?=null
}