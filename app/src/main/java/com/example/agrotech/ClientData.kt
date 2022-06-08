package com.example.agrotech

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.agrotech.MainActivity.Companion.globalVar
import com.example.agrotech.interfaces.UserService
import com.example.agrotech.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientData: AppCompatActivity() {

    lateinit var name:TextView
    lateinit var email:TextView
    lateinit var dni:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_data)
        val button = findViewById<ImageView>(R.id.clientedit)
        val button2= findViewById<ImageView>(R.id.back)
        button.setOnClickListener{
            val intent = Intent(this,ClientEdit::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        name=findViewById(R.id.textView3)
        email=findViewById(R.id.textView4)
        dni=findViewById(R.id.button2)
        val id=1
        val userService: UserService = Retro().getRetroClient().create(UserService::class.java)
        userService.getUserData(globalVar).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>?) {

                if (response != null) {
                    name.setText(response.body().name +" "+ response.body().lastname)
                    email.setText("CORREO: "+response.body().email)
                    dni.setText("DNI: "+response.body().dni.toString())
                }
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Toast.makeText(this@ClientData,"Error", Toast.LENGTH_LONG).show()
            }

        })

    }
}