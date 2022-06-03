package com.example.agrotech

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    fun getRetroClient(): Retrofit {
        val gson= GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}