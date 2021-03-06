package com.example.agrotech

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agrotech.interfaces.TreatService
import com.example.agrotech.models.Treat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

class Treat:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.treat)

        val btAdd = findViewById<Button>(R.id.add)
        btAdd.setOnClickListener{
            val description = findViewById<EditText>(R.id.editextDescp).text.toString()
            val type = findViewById<EditText>(R.id.etReceta).text.toString()
            val certificate = findViewById<EditText>(R.id.etComment).text.toString()

            val treat=  Treat()
            treat.description=description
            treat.type=type
            treat.certificate = certificate

            println(description)
            val treatService: TreatService = Retro().getRetroClient().create(TreatService::class.java)
            treatService.addTreat(MainActivity.globalVar,treat) .enqueue(object :
                Callback<Treat> {
                override fun onResponse(call: Call<Treat>?, response: Response<Treat>?) {

                    Toast.makeText(this@Treat,"Se Agrego el tratamiento", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Treat>?, t: Throwable?) {
                    Toast.makeText(this@Treat,"Error", Toast.LENGTH_LONG).show()

                }

            })
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.iconclient -> {
                startActivity( Intent(this@Treat , ClientData::class.java))
            }
            R.id.iconnotify -> {
                startActivity( Intent(this@Treat , Notification::class.java))
            }

            R.id.close -> {
                var builder= AlertDialog.Builder(this@Treat)
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