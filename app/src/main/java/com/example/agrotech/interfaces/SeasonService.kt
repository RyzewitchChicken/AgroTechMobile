package com.example.agrotech.interfaces

import com.example.agrotech.models.Content
import com.example.agrotech.models.Season
import com.example.agrotech.models.SeasonContent
import com.example.agrotech.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET



interface SeasonService {

    @GET("seasons")
    fun getSeason(): Call<SeasonContent>
}