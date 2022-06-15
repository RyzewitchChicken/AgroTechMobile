package com.example.agrotech.models

import com.google.gson.annotations.SerializedName

class PlagueContent {
    @SerializedName("content")
    var content:List<Plague>? = null
}