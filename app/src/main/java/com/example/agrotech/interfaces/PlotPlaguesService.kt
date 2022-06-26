package com.example.agrotech.interfaces

import com.example.agrotech.models.PlotContent
import com.example.agrotech.models.PlotPlaguesContent
import retrofit2.Call
import retrofit2.http.GET

interface PlotPlaguesService {
    @GET("plotPlagues")
    fun getPlotPlagues(): Call<PlotPlaguesContent>
}