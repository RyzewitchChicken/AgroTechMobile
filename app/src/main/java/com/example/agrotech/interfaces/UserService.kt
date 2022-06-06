package com.example.agrotech.interfaces

import com.example.agrotech.models.Content
import com.example.agrotech.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("users")
    fun login(): Call<Content>

    @GET("users/{userId}")
    fun getUserData(@Path("userId") id:Int ): Call<User>
    @PUT("users/{userId}")
    fun updateUserData(@Path("userId") id :Int, @Body user:User): Call<User>
}