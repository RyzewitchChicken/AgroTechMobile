package com.example.agrotech

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agrotech.interfaces.PlagueService
import com.example.agrotech.models.PlagueContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Notification:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification)
        val button = findViewById<ImageView>(R.id.back)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val temper=findViewById<TextView>(R.id.temp)
        val humid=findViewById<TextView>(R.id.humi)
        val plague=findViewById<TextView>(R.id.namep)

        val temper1=findViewById<TextView>(R.id.temp2)
        val humid1=findViewById<TextView>(R.id.humi2)
        val plague1=findViewById<TextView>(R.id.name1)

        val plagueService: PlagueService = Retro().getRetroClient().create(PlagueService::class.java)
        plagueService.getPlague().enqueue(object: Callback<PlagueContent> {
            override fun onResponse(call: Call<PlagueContent>?, response: Response<PlagueContent>?) {
                if (response != null) {
                    temper.text= "Temperatura: " + response.body().content?.get(2)?.temperatureThreshold.toString()
                    humid.text= "Humedad: " + response.body().content?.get(2)?.humidityThreshold.toString()
                    plague.text= response.body().content?.get(2)?.type.toString()

                    //temper1.text= "Temperatura: " + response.body().content?.get(1)?.temperatureThreshold.toString()
                    //humid1.text= "Humedad: " + response.body().content?.get(1)?.humidityThreshold.toString()
                    //plague1.text= response.body().content?.get(1)?.type.toString()

                }
            }

            override fun onFailure(call: Call<PlagueContent>?, t: Throwable?) {
                Toast.makeText(this@Notification,"Error", Toast.LENGTH_LONG).show()
            }

        })
    }
}