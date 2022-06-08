package com.example.agrotech.models

import com.google.gson.annotations.SerializedName

class Plague {
    @SerializedName("id")
    var id: Int?=null

    @SerializedName("description")
    var description: String?=null

    @SerializedName("type")
    var type: String?=null

    @SerializedName("phThreshold")
    var phThreshold: String?=null

    @SerializedName("temperatureThreshold")
    var temperatureThreshold: String?=null

    @SerializedName("humidityThreshold")
    var humidityThreshold: String?=null

    @SerializedName("co2Threshold")
    var co2Threshold: String?=null

    @SerializedName("electricConductivityThreshold")
    var electricConductivityThreshold: String?=null
}