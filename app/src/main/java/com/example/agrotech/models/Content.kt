package com.example.agrotech.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Content {
    @SerializedName("content")
    var content:List<User>? = null
}