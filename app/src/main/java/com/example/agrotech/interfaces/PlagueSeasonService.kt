package com.example.agrotech.interfaces

import com.example.agrotech.models.PlagueContent
import com.example.agrotech.models.PlagueSeasonContent
import retrofit2.Call
import retrofit2.http.GET

interface PlagueSeasonService {

    @GET("plagueSeasons")
    fun getPlagueSeasons(): Call<PlagueSeasonContent>
}