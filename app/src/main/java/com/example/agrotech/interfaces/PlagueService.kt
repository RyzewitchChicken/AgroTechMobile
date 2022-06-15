package com.example.agrotech.interfaces

import com.example.agrotech.models.PlagueContent
import retrofit2.Call
import retrofit2.http.GET

interface PlagueService {
    @GET("plagues")
    fun getPlague(): Call<PlagueContent>
}