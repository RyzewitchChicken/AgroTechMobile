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
import com.example.agrotech.interfaces.PlotService
import com.example.agrotech.models.Plot
import com.example.agrotech.models.PlotContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

class ViewPlot:AppCompatActivity() {

    lateinit var names:String
    var ids:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_plot)

        val plot = intent.getParcelableExtra<Plot>("actualplot")
        if (plot != null){
            val plotdesc = findViewById<EditText>(R.id.plotdesc)
            val plotloca = findViewById<EditText>(R.id.plotloca)
            val plotarea = findViewById<EditText>(R.id.plotarea)
            val plotvol = findViewById<EditText>(R.id.plotvolu)

            plotdesc.setText(plot.description)
            plotloca.setText(plot.location)
            plotarea.setText(plot.area.toString())
            plotvol.setText(plot.volume.toString())

        }



        val btPlaga = findViewById<Button>(R.id.btPlaga)
        btPlaga.setOnClickListener {
            val intent = Intent(this, Plague::class.java)
            startActivity(intent)
        }

        val editbutton = findViewById<Button>(R.id.btVerPlot)
        editbutton.setOnClickListener {
            val intent = Intent(this, EditPlot::class.java)
            startActivity(intent)
        }

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