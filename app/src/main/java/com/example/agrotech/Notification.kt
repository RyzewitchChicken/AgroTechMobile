package com.example.agrotech

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agrotech.interfaces.PlagueService
import com.example.agrotech.models.PlagueContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Notification:AppCompatActivity() {
    lateinit var temps:String
    lateinit var humis:String
    lateinit var nameps:String
    var tempAr = ArrayList<String>()
    var humiAr = ArrayList<String>()
    var namepAr = ArrayList<String>()
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



        val plagueService: PlagueService = Retro().getRetroClient().create(PlagueService::class.java)
        plagueService.getPlague().enqueue(object: Callback<PlagueContent> {
            override fun onResponse(call: Call<PlagueContent>?, response: Response<PlagueContent>?) {
                if (response != null) {
                    try {
                        var notifyData = response?.body()?.content
                        if (notifyData != null) {
                            var i = 0
                            while (i < notifyData.size) {
                                val jsonObject = notifyData.get(i)
                                temps= jsonObject.temperatureThreshold.toString()
                                humis= jsonObject.humidityThreshold.toString()
                                nameps= jsonObject.type.toString()
                                i++
                                tempAr.add(temps)
                                humiAr.add(humis)
                                namepAr.add(nameps)

                            }


                        }

                    } catch (e: Exception) {

                    }
                    val recycler=findViewById<RecyclerView>(R.id.recycler)

                    val adapter=CustomAdapter(tempAr,humiAr,namepAr)
                    recycler.layoutManager=LinearLayoutManager(this@Notification)
                    recycler.adapter=adapter
                }
            }

            override fun onFailure(call: Call<PlagueContent>?, t: Throwable?) {
                Toast.makeText(this@Notification,"Error", Toast.LENGTH_LONG).show()
            }

        })

    }
}