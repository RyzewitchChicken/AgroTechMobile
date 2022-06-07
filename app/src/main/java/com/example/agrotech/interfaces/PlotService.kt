package com.example.agrotech.interfaces

import android.widget.EditText
import com.example.agrotech.models.Plot
import retrofit2.Call
import retrofit2.http.*

interface PlotService {

    @POST("users/{userId}/plots")
    fun addPlot(@Path("userId") id:Int, @Body data: Plot
                     ): Call<Plot>
}