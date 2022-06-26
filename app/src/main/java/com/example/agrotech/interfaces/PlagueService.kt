package com.example.agrotech.interfaces

import com.example.agrotech.models.Plague
import com.example.agrotech.models.PlagueContent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlagueService {
    @GET("plagues")
    fun getPlague(): Call<PlagueContent>

    @GET("plagues/{plagueId}")
    fun getPlaguebyId(@Path("plagueId") id: Int):Call<Plague>
}