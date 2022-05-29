package com.example.agrotech

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ClientEdit: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_edit)
        val button = findViewById<ImageView>(R.id.back)
        button.setOnClickListener{
            val intent = Intent(this,ClientData::class.java)
            startActivity(intent)
        }
    }

}