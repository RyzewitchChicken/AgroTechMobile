package com.example.agrotech.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")

    var name: String?=null
    @SerializedName("lastname")

    var lastname: String?=null
    @SerializedName("dni")

    var dni: Int?=null
    @SerializedName("cellphoneNumber")

    var cellphoneNumber: Int?=null
    @SerializedName("email")

    var email: String?=null
    @SerializedName("password")

    var password: String?=null
    @SerializedName("profileImage")

    var profileImage: String?=null
    @SerializedName("accessType")

    var accessType: Boolean?=null





}