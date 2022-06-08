package com.example.agrotech

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agrotech.interfaces.PlotService
import com.example.agrotech.interfaces.UserService
import com.example.agrotech.models.Content
import com.example.agrotech.models.PlotContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

class ViewPlot:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_plot)
        val plotdesc=findViewById<EditText>(R.id.plotdesc)
        val plotloca=findViewById<EditText>(R.id.plotloca)
        val plotarea=findViewById<EditText>(R.id.plotarea)
        val plotvol=findViewById<EditText>(R.id.plotvolu)


        val plotService: PlotService = Retro().getRetroClient().create(PlotService::class.java)
        plotService.getPlots().enqueue(object : Callback<PlotContent> {
            override fun onResponse(call: Call<PlotContent>?, response: Response<PlotContent>?) {

                if (response != null) {
                    plotdesc.setText(response.body().content?.get(0)?.description.toString())
                    plotloca.setText(response.body().content?.get(0)?.location.toString())
                    plotvol.setText(response.body().content?.get(0)?.volume.toString())
                    plotarea.setText(response.body().content?.get(0)?.area.toString())

                }

            }

            override fun onFailure(call: Call<PlotContent>?, t: Throwable?) {
                TODO("Not yet implemented")
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