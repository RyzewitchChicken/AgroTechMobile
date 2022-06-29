package com.example.agrotech.models

import com.google.gson.annotations.SerializedName

class PlagueSeason {

    @SerializedName("id")
    var id: Int?=null

    @SerializedName("plagueId")
    var plagueId: Int?=null

    @SerializedName("seasonId")
    var seasonId: Int?=null
}