package com.example.agrotech

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agrotech.MainActivity.Companion.globalVar
import com.example.agrotech.interfaces.UserService
import com.example.agrotech.models.Content
import com.example.agrotech.models.User
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LogIn : AppCompatActivity() {
    lateinit var editEmail: EditText
    lateinit var editpass: EditText
    lateinit var emails:String
    lateinit var passes:String
    var code:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val button = findViewById<Button>(R.id.into)
        editEmail=findViewById(R.id.edituser)
        editpass=findViewById(R.id.editpass)
        editEmail.setOnClickListener{
            editEmail.text.clear()
            editpass.text.clear()
        }
        button.setOnClickListener{
            callServiceGetUsers()
            //val intent = Intent(this@LogIn,MainActivity::class.java)
            //startActivity(intent)
       }

    }

    private fun callServiceGetUsers() {
        if(editEmail.text.toString().isEmpty() || editpass.text.toString().isEmpty()) {
            Toast.makeText(this@LogIn, "Ingresar usuario y contraseña",Toast.LENGTH_SHORT).show()
        } else {
            val userService: UserService = Retro().getRetroClient().create(UserService::class.java)
            userService.login().enqueue(object : Callback<Content> {
                override fun onResponse(call: Call<Content>?, response: Response<Content>?) {
                   /* if (response != null) {
                        globalVar = response.body().content?.get(0)?.id!!
                    }*/
                    try {
                        var userData = response?.body()?.content
                        if (userData != null) {
                            var emailAr = ArrayList<String>()
                            var passAr = ArrayList<String>()
                            var i = 0
                            while (i < userData.size) {
                                val jsonObject = userData.get(i)
                                emails= jsonObject.email.toString()
                                passes= jsonObject.password.toString()
                                code=jsonObject.id!!
                                i++
                                emailAr.add(emails)
                                passAr.add(passes)
                                println(emailAr)
                            }

                            if (emailAr.contains(editEmail.text.toString()) && passAr.contains(editpass.text.toString())) {
                                for ((index, value) in emailAr.withIndex()) {
                                    if (editEmail.text.toString() == value) {
                                        if (response != null) {
                                            globalVar = response.body().content?.get(index)?.id!!
                                        }


                                    }

                                }
                                val intent = Intent(this@LogIn, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@LogIn, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
                            }

                        }

                    } catch (e: Exception) {

                    }


                }
                override fun onFailure(call: Call<Content>?, t: Throwable?) {
                    Toast.makeText(this@LogIn, "Error", Toast.LENGTH_LONG).show()
                }

            })
        }
    }

}