package com.example.agrotech

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.agrotech.interfaces.PlagueService
import com.example.agrotech.interfaces.PlotPlaguesService
import com.example.agrotech.interfaces.PlotService
import com.example.agrotech.models.Plague
import com.example.agrotech.models.Plot
import com.example.agrotech.models.PlotContent
import com.example.agrotech.models.PlotPlaguesContent
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

class DetailsPlot:AppCompatActivity() {
    lateinit var names:String
    var ids:Int = 0
    var userids:Int = 0
    lateinit var plotPlaguesIds:String
    lateinit var plaguesIds:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_plot)
        val namePlague=findViewById<EditText>(R.id.detailplague)
        val plotdesc=findViewById<EditText>(R.id.detaildesc)
        val plotarea=findViewById<EditText>(R.id.detailarea)
        val plotvol=findViewById<EditText>(R.id.detailvol)
        val plotloca=findViewById<EditText>(R.id.detailloca)
        val plotim=findViewById<ImageView>(R.id.detailimage)
        val emptyimage=findViewById<TextView>(R.id.noimage)
        val noPlague=findViewById<TextView>(R.id.noplagues)
        val btPla=findViewById<Button>(R.id.btPlaga)
        val plotService: PlotService = Retro().getRetroClient().create(PlotService::class.java)
        plotService.getPlots().enqueue(object : Callback<PlotContent> {
            override fun onResponse(call: Call<PlotContent>?, response: Response<PlotContent>?) {

                try {
                    var plotData = response?.body()?.content
                    if (plotData != null) {
                        var namePl = ArrayList<String>()
                        var idsPl = ArrayList<Int>()
                        var useridsPl = ArrayList<Int>()
                        var i = 0
                        while (i < plotData.size) {
                            val jsonObject = plotData.get(i)
                            names= jsonObject.name.toString()
                            ids= jsonObject.id.toString().toInt()
                            userids= jsonObject.userId.toString().toInt()
                            i++
                            namePl.add(names)
                            idsPl.add(ids)
                            useridsPl.add(userids)
                            useridsPl.add(ids)
                        }
                        val spinner=findViewById<Spinner>(R.id.spinner)
                        val lista= namePl.toList()
                        println(namePl)
                        println(useridsPl)
                        println(plotData)
                        println("este es su id: "+ MainActivity.globalVar)
                        val spn= ArrayAdapter(this@DetailsPlot,android.R.layout.simple_spinner_dropdown_item,lista)
                        spinner.adapter=spn
                        spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                val namepos= position
                                println(namepos)
                                println(idsPl[namepos])
                                val plotService: PlotService = Retro().getRetroClient().create(
                                    PlotService::class.java)
                                plotService.getPlotsById(idsPl[namepos]).enqueue(object :
                                    Callback<Plot> {
                                    override fun onResponse(call: Call<Plot>?, response: Response<Plot>?) {
                                        if (response != null) {
                                            plotdesc.setText(response.body().description.toString())
                                            plotloca.setText(response.body().location.toString())
                                            plotvol.setText(response.body().volume.toString())
                                            plotarea.setText(response.body().area.toString())
                                            if (response.body().plotImage.isNullOrEmpty()) {
                                                Picasso.get().load("no existe imagen").into(plotim)
                                                emptyimage.text = "No tiene imagen"
                                            } else {
                                                Picasso.get().load(response.body().plotImage).into(plotim)
                                            }

                                        }

                                    }
                                    override fun onFailure(call: Call<Plot>?, t: Throwable?) {
                                        Toast.makeText(this@DetailsPlot,"Error", Toast.LENGTH_LONG).show()
                                    }
                                })
                                val plotPlaguesService: PlotPlaguesService = Retro().getRetroClient().create(PlotPlaguesService::class.java)
                                plotPlaguesService.getPlotPlagues().enqueue(object :Callback<PlotPlaguesContent> {
                                    override fun onResponse(call: Call<PlotPlaguesContent>?, response: Response<PlotPlaguesContent>?) {
                                        try {
                                            var plotplagueData = response?.body()?.content
                                            if (plotplagueData != null) {
                                                var plotPlIds = ArrayList<String>()
                                                var plIds = ArrayList<String>()
                                                var i = 0
                                                while (i < plotplagueData.size) {
                                                    val jsonObject = plotplagueData.get(i)
                                                    plotPlaguesIds= jsonObject.plotId.toString()
                                                    plaguesIds=jsonObject.plagueId.toString()
                                                    i++
                                                    plotPlIds.add(plotPlaguesIds)
                                                    plIds.add(plaguesIds)


                                                }
                                                if(plotPlIds.contains(idsPl[namepos].toString())) {
                                                    noPlague.text="Tiene Plaga"
                                                    for ((index, value) in plotPlIds.withIndex()) {
                                                        if (idsPl[namepos].toString() == value) {
                                                            if (response != null) {
                                                                var idPlague = response.body().content?.get(index)?.plagueId
                                                                val plagueService: PlagueService = Retro().getRetroClient().create(PlagueService::class.java)
                                                                if (idPlague != null) {
                                                                    plagueService.getPlaguebyId(idPlague) .enqueue(object :Callback<Plague> {
                                                                        override fun onResponse(call: Call<Plague>?, response: Response<Plague>?) {

                                                                            if (response != null) {
                                                                                namePlague.isVisible =true
                                                                                namePlague.setText(response.body().type.toString())
                                                                                btPla.isVisible=true
                                                                                btPla.setOnClickListener {
                                                                                    val intent = Intent(this@DetailsPlot, com.example.agrotech.Plague::class.java)
                                                                                    intent.putExtra("Plaga",response.body().type.toString())
                                                                                    startActivity(intent)
                                                                                }
                                                                            }

                                                                        }

                                                                        override fun onFailure(call: Call<Plague>?, t: Throwable?) {
                                                                            Toast.makeText(this@DetailsPlot,"Error", Toast.LENGTH_LONG).show()
                                                                        }

                                                                    })

                                                                }
                                                            }


                                                        }

                                                    }

                                                } else {
                                                    namePlague.isVisible =false
                                                    btPla.isVisible=false
                                                    noPlague.text="No tiene plaga"
                                                }

                                            }

                                        } catch (e: Exception) {

                                        }
                                    }

                                    override fun onFailure(call: Call<PlotPlaguesContent>?, t: Throwable?) {
                                        Toast.makeText(this@DetailsPlot,"Error", Toast.LENGTH_LONG).show()
                                    }

                                })
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Toast.makeText(this@DetailsPlot,"No existen parcelas", Toast.LENGTH_LONG).show()
                            }



                        }
                    }

                } catch (e: Exception) {

                }

            }

            override fun onFailure(call: Call<PlotContent>?, t: Throwable?) {
                Toast.makeText(this@DetailsPlot,"No existen parcelas", Toast.LENGTH_LONG).show()
            }


        })
    }








    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.iconclient -> {
                startActivity( Intent(this@DetailsPlot , ClientData::class.java))
            }
            R.id.iconnotify -> {
                startActivity( Intent(this@DetailsPlot , Notification::class.java))
            }

            R.id.close -> {
                var builder= AlertDialog.Builder(this@DetailsPlot)
                builder.setTitle("Cerrar Sesión")
                builder.setMessage("¿Desea cerrar sesión?")
                builder.setPositiveButton("Si", DialogInterface.OnClickListener{
                        dialog, id ->
                    moveTaskToBack(true)
                    exitProcess(-1)
                    dialog.cancel()
                })
                builder.setNegativeButton("No", DialogInterface.OnClickListener{
                        dialog, id -> dialog.cancel()
                })
                var alert: AlertDialog =builder.create()
                alert.show()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_icons,menu)

        return super.onCreateOptionsMenu(menu)
    }
}