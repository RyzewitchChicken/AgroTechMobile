package com.example.agrotech.interfaces

import com.example.agrotech.models.Treat
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface TreatService {
    @POST("users/{treatId}/treats")
    fun addTreat(@Path("treatId") id:Int, @Body data: Treat): Call<Treat>
}