package com.example.agrotech

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agrotech.MainActivity.Companion.globalVar
import com.example.agrotech.interfaces.PlotService
import com.example.agrotech.interfaces.UserService
import com.example.agrotech.models.Content
import com.example.agrotech.models.Plot
import com.example.agrotech.models.PlotContent
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

class ViewPlot:AppCompatActivity() {

    lateinit var names:String
    var ids:Int = 0
    var userids:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_plot)
        val plotdesc=findViewById<EditText>(R.id.plotdesc)
        val plotloca=findViewById<EditText>(R.id.plotloca)
        val plotarea=findViewById<EditText>(R.id.plotarea)
        val plotvol=findViewById<EditText>(R.id.plotvolu)
        val plotim=findViewById<ImageView>(R.id.plotimage)
        val editbutton = findViewById<Button>(R.id.editplot)
        val details=findViewById<Button>(R.id.details)
        editbutton.setOnClickListener {
            val intent = Intent(this, EditPlot::class.java)
            startActivity(intent)
        }
        details.setOnClickListener{
            val intent=Intent(this,DetailsPlot::class.java)
            startActivity(intent)
        }

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
                    println("este es su id: "+globalVar)
                    val spn=ArrayAdapter(this@ViewPlot,android.R.layout.simple_spinner_dropdown_item,lista)
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
                            val plotService: PlotService = Retro().getRetroClient().create(PlotService::class.java)
                            plotService.getPlotsById(idsPl[namepos]).enqueue(object : Callback<Plot> {
                                override fun onResponse(call: Call<Plot>?, response: Response<Plot>?) {
                                    if (response != null) {
                                        plotdesc.setText(response.body().description.toString())
                                        plotloca.setText(response.body().location.toString())
                                        plotvol.setText(response.body().volume.toString())
                                        plotarea.setText(response.body().area.toString())
                                        if (response.body().plotImage.isNullOrEmpty()) {
                                            Picasso.get().load("no existe imagen").into(plotim)
                                        } else {
                                            Picasso.get().load(response.body().plotImage).into(plotim)
                                        }

                                    }

                                }
                                override fun onFailure(call: Call<Plot>?, t: Throwable?) {
                                    Toast.makeText(this@ViewPlot,"Error", Toast.LENGTH_LONG).show()
                                }
                            })
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            Toast.makeText(this@ViewPlot,"No existen parcelas", Toast.LENGTH_LONG).show()
                        }

                    }
                    }

                } catch (e: Exception) {

                }

            }

            override fun onFailure(call: Call<PlotContent>?, t: Throwable?) {
                Toast.makeText(this@ViewPlot,"No existen parcelas", Toast.LENGTH_LONG).show()
            }


        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.iconclient -> {
                startActivity( Intent(this@ViewPlot , ClientData::class.java))
            }
            R.id.iconnotify -> {
                startActivity( Intent(this@ViewPlot , Notification::class.java))
            }

            R.id.close -> {
                var builder= AlertDialog.Builder(this@ViewPlot)
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