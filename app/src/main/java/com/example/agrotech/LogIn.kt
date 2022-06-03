package com.example.agrotech

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agrotech.interfaces.UserService
import com.example.agrotech.models.Content
import com.example.agrotech.models.User
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LogIn : AppCompatActivity() {
    lateinit var editEmail: EditText
    lateinit var editpass: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val button = findViewById<Button>(R.id.into)
        editEmail=findViewById(R.id.edituser)
        editpass=findViewById(R.id.editpass)
        button.setOnClickListener{
            //callServiceGetUsers()
            val intent = Intent(this@LogIn,MainActivity::class.java)
            startActivity(intent)

       }

    }

    private fun callServiceGetUsers() {
        val userService: UserService = Retro().getRetroClient().create(UserService::class.java)
        userService.login().enqueue(object: Callback<Content> {
            override fun onResponse(call: Call<Content>?, response: Response<Content>?) {
                var name= response?.body()?.content?.get(0)?.name
                println(name)
            }

            override fun onFailure(call: Call<Content>?, t: Throwable?) {
                Toast.makeText(this@LogIn,"Error",Toast.LENGTH_LONG).show()
            }

        })
    }
    private fun callServiceGetUsersData() {
        val id=1
        val userService: UserService = Retro().getRetroClient().create(UserService::class.java)
        userService.getUserData(id).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                Toast.makeText(this@LogIn,"Ok",Toast.LENGTH_LONG).show()
                if (response != null) {
                    println(response.body().name)
                }
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Toast.makeText(this@LogIn,"Error",Toast.LENGTH_LONG).show()
            }

        })
    }
    private fun callotherService() {

        val userService: UserService = Retro().getRetroClient().create(UserService::class.java)
        userService.login().enqueue(object : Callback<Content> {
            override fun onResponse(call: Call<Content>?, response: Response<Content>?) {
                val user= response?.body()
                user!!.content?.toString()?.let { Log.e("id", it) }
            }

            override fun onFailure(call: Call<Content>?, t: Throwable?) {
                Toast.makeText(this@LogIn,"Error",Toast.LENGTH_LONG).show()
            }

        })
    }
}