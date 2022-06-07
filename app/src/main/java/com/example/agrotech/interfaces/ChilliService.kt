package com.example.agrotech.interfaces

import com.example.agrotech.models.Chilli
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ChilliService {

    @POST("users/{chilliId}/chillis")
    fun addChilli(@Path("userId") id:Int, @Body data: Chilli
    ): Call<Chilli>
}