package com.example.agrotech

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agrotech.MainActivity.Companion.globalVar
import com.example.agrotech.interfaces.PlotService
import com.example.agrotech.interfaces.UserService
import com.example.agrotech.models.Content
import com.example.agrotech.models.Plot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

class AddPlot : AppCompatActivity() {
    lateinit var add:Button
    lateinit var ar:EditText
    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_plot)
        add=findViewById(R.id.add)
        add.setOnClickListener{
            val description = findViewById<EditText>(R.id.desc).text.toString()
            val location = findViewById<EditText>(R.id.plotstatus).text.toString()
            val area = findViewById<EditText>(R.id.area).text.toString().toFloat()
            val volume = findViewById<EditText>(R.id.volume).text.toString().toFloat()

            val plotdata=  Plot()
            plotdata.description=description
            plotdata.location=location
            plotdata.area=area
            plotdata.volume=volume
            plotdata.name="nombre"
            plotdata.plotImage="imagen"
            plotdata.userId= globalVar
            plotdata.id = area.toInt()
            println(description)
            val plotService: PlotService = Retro().getRetroClient().create(PlotService::class.java)
            plotService.addPlot(globalVar,plotdata) .enqueue(object :Callback<Plot> {
                override fun onResponse(call: Call<Plot>?, response: Response<Plot>?) {

                    Toast.makeText(this@AddPlot, "Se Agrego la Parcela", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Plot>?, t: Throwable?) {
                    Toast.makeText(this@AddPlot,"Error",Toast.LENGTH_LONG).show()

                }

            })


        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.iconclient -> {
                startActivity( Intent(this@AddPlot , ClientData::class.java))
            }
            R.id.iconnotify -> {
                startActivity( Intent(this@AddPlot , Notification::class.java))
            }
            R.id.close -> {
                var builder= AlertDialog.Builder(this@AddPlot)
                builder.setTitle("Cerrar Sesi??n")
                builder.setMessage("??Desea cerrar sesi??n?")
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