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
import kotlin.random.Random

class Notification:AppCompatActivity() {
    lateinit var temps:String
    lateinit var humis:String
    lateinit var nameps:String
    var tempAr = ArrayList<String>()
    var humiAr = ArrayList<String>()
    var namepAr = ArrayList<String>()
    var nameplagues = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification)
        val button = findViewById<ImageView>(R.id.back)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val tvUmbral = findViewById<TextView>(R.id.tvdescripUmbral)

        var tempRange : String
        tempRange = "0"

        val temporada = Random.nextInt(0, 2)

        val rand =  Random.nextInt(10, 40)
        val rand2 = rand

        if (rand == 30){
            tempRange = "30°C"
            nameplagues.add("Fusarium")
        }
        if (rand >= 12 && rand <= 32){
            tempRange = "12°C - 32°C"
            nameplagues.add("Rhizoctonia Solani")
        }
        if (rand >= 17 && rand <= 23){
            tempRange = "17°C - 23°C"
            nameplagues.add("Botrytis cinerea")
        }
        if (rand >= 25 && rand <= 40){
            tempRange = "25°C - 40°C"
            nameplagues.add("Alternaria sp")
        }

        if (rand2 < 12){
            tvUmbral.text = this.getString(R.string.umbral12)
        }else if (rand2 < 15){
            tvUmbral.text = this.getString(R.string.umbral15)
        }else if (rand2 < 17){
            tvUmbral.text = this.getString(R.string.umbral17)
        }else if (rand2 < 20){
            tvUmbral.text = this.getString(R.string.umbral20)
        }else if (rand2 < 25){
            tvUmbral.text = this.getString(R.string.umbral25)
        }else if (rand2 < 30){
            tvUmbral.text = this.getString(R.string.umbral20)
        }else{
            tvUmbral.text = this.getString(R.string.noumbral)
        }



        val temper=findViewById<TextView>(R.id.temp)
        val humid=findViewById<TextView>(R.id.humi)
        val plague=findViewById<TextView>(R.id.namep)
        val temperatura = findViewById<TextView>(R.id.tvTemp)

        temperatura.text = rand.toString() + "°C"

        val tvtemporada = findViewById<TextView>(R.id.tvTemporada)
        val tvDescTemp = findViewById<TextView>(R.id.tvdescriptemp)

        if (temporada == 0){
            tvtemporada.text = this.getString(R.string.Invierno)
            tvDescTemp.text = this.getString(R.string.descTempInv)
        }else{
            tvtemporada.text = this.getString(R.string.Verano)
            tvDescTemp.text = this.getString(R.string.descTempVer)
        }
        //temperatura.text = this.getString(R.string.descTemp)




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

                                for (i in nameplagues.iterator()){
                                    if (nameps == i){
                                        tempAr.add(temps)
                                        humiAr.add(humis)
                                        namepAr.add(nameps)
                                    }
                                }




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

    fun getPlagues(rand: Int){
        if (rand == 30){
            tempAr.add(temps)
            humiAr.add(humis)
            namepAr.add(nameps)
        }
        if (rand >= 12 && rand <= 32){
            tempAr.add(temps)
            humiAr.add(humis)
            namepAr.add(nameps)
        }
        if (rand >= 17 && rand <= 23){
            tempAr.add(temps)
            humiAr.add(humis)
            namepAr.add(nameps)
        }
        if (rand >= 25 && rand <= 40){
            tempAr.add(temps)
            humiAr.add(humis)
            namepAr.add(nameps)
        }
        if (rand >= 20 && rand <= 30){
            tempAr.add(temps)
            humiAr.add(humis)
            namepAr.add(nameps)
        }
        if (rand >= 15 && rand <= 23){
            tempAr.add(temps)
            humiAr.add(humis)
            namepAr.add(nameps)
        }
        if (rand >= 25 && rand <= 30){
            tempAr.add(temps)
            humiAr.add(humis)
            namepAr.add(nameps)
        }


    }
}