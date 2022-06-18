package com.example.agrotech

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agrotech.interfaces.PlotService
import com.example.agrotech.models.Plot
import com.example.agrotech.models.PlotContent
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess


class EditPlot:AppCompatActivity() {
    lateinit var names:String
    var ids:Int = 0
    var idedit:Int=0
    lateinit var plotname:String
    var useredit:Int=0
    var plotlink:String=""
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_plot)
        val plotdesc=findViewById<EditText>(R.id.descedit)
        val plotloca=findViewById<EditText>(R.id.locaedit)
        val plotarea=findViewById<EditText>(R.id.areaedit)
        val plotvol=findViewById<EditText>(R.id.voluedit)

        val editbutton=findViewById<Button>(R.id.editplotdata)
        val plotDataEdit=Plot()
        val addplot=findViewById<Button>(R.id.ploteditphoto)
        addplot.setOnClickListener {
            var builder2= AlertDialog.Builder(this@EditPlot)
            builder2.setTitle("Ingrese Link de Imagen")
            var input:EditText
            input =  EditText(this);
            builder2.setView(input)
            builder2.setPositiveButton("Agregar", DialogInterface.OnClickListener{
                    dialog, id ->
                dialog.dismiss()
                plotlink=input.text.toString()

            })
            builder2.setNegativeButton("Cancelar", DialogInterface.OnClickListener{
                    dialog, id -> dialog.cancel()
            })
            var alert2: AlertDialog =builder2.create()
            alert2.show()
        }

        editbutton.setOnClickListener() {
            plotDataEdit.description=plotdesc.text.toString()
            plotDataEdit.area=plotarea.text.toString().toFloat()
            plotDataEdit.location=plotloca.text.toString()
            plotDataEdit.volume=plotvol.text.toString().toFloat()
            plotDataEdit.plotImage=plotlink
            plotDataEdit.name=plotname
            plotDataEdit.userId=useredit

            val plotService: PlotService = Retro().getRetroClient().create(PlotService::class.java)
            plotService.updatePlotData(idedit,plotDataEdit) .enqueue(object : Callback<Plot> {
                override fun onResponse(call: Call<Plot>?, response: Response<Plot>?) {
                    Toast.makeText(this@EditPlot,"Datos Actualizados", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Plot>?, t: Throwable?) {
                    Toast.makeText(this@EditPlot,"Error", Toast.LENGTH_LONG).show()
                }
            })
        }

        val plotService: PlotService = Retro().getRetroClient().create(PlotService::class.java)
        plotService.getPlots().enqueue(object : Callback<PlotContent> {
            override fun onResponse(call: Call<PlotContent>?, response: Response<PlotContent>?) {
                try {
                    var plotData = response?.body()?.content
                    if (plotData != null) {
                        var namePl = ArrayList<String>()
                        var idsPl = ArrayList<Int>()
                        var i = 0
                        while (i < plotData.size) {
                            val jsonObject = plotData.get(i)
                            names= jsonObject.name.toString()
                            ids= jsonObject.id.toString().toInt()

                            i++
                            namePl.add(names)
                            idsPl.add(ids)
                            println(namePl)
                            println(idsPl)
                        }
                        val spinner=findViewById<Spinner>(R.id.spinner)
                        val lista= namePl.toList()
                        val spn= ArrayAdapter(this@EditPlot,android.R.layout.simple_spinner_dropdown_item,lista)
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
                                            idedit=idsPl[namepos]
                                            plotname=response.body().name.toString()
                                            useredit=response.body().userId.toString().toInt()
                                        }

                                    }
                                    override fun onFailure(call: Call<Plot>?, t: Throwable?) {
                                        Toast.makeText(this@EditPlot,"Error", Toast.LENGTH_LONG).show()
                                    }
                                })

                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Toast.makeText(this@EditPlot,"No existen parcelas", Toast.LENGTH_LONG).show()
                            }

                        }

                    }
                } catch (e: Exception) {

                }

            }

            override fun onFailure(call: Call<PlotContent>?, t: Throwable?) {
                Toast.makeText(this@EditPlot,"No existen parcelas", Toast.LENGTH_LONG).show()
            }


        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.iconclient -> {
                startActivity( Intent(this@EditPlot , ClientData::class.java))
            }
            R.id.iconnotify -> {
                startActivity( Intent(this@EditPlot , Notification::class.java))
            }

            R.id.close -> {
                var builder= AlertDialog.Builder(this@EditPlot)
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