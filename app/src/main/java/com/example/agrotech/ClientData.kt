package com.example.agrotech

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity

class ClientData: AppCompatActivity() {


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
    }
}