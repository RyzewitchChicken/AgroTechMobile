package com.example.agrotech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Plague : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plague)

        val btnSave = findViewById<Button>(R.id.btSavePlague)

        btnSave.setOnClickListener{
            val intent = Intent(this, ViewPlot::class.java)
            startActivity(intent)

            Toast.makeText(this@Plague,"Se Agrego la descripcion de plaga", Toast.LENGTH_LONG).show()
        }
    }
}