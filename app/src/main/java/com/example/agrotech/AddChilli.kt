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
import com.example.agrotech.interfaces.ChilliService
import com.example.agrotech.models.Chilli
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

class AddChilli:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_chilli)

        val btAdd = findViewById<Button>(R.id.add)
        btAdd.setOnClickListener{
            val description = findViewById<EditText>(R.id.etDescription).text.toString()
            val name = findViewById<EditText>(R.id.etTipo).text.toString()

            val chilli=  Chilli()
            chilli.description=description
            chilli.name=name

            println(description)
            val chilliService: ChilliService = Retro().getRetroClient().create(ChilliService::class.java)
            chilliService.addChilli(MainActivity.globalVar,chilli) .enqueue(object : Callback<Chilli> {
                override fun onResponse(call: Call<Chilli>?, response: Response<Chilli>?) {

                    Toast.makeText(this@AddChilli,"Se Agrego el Aji", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Chilli>?, t: Throwable?) {
                    Toast.makeText(this@AddChilli,"Error", Toast.LENGTH_LONG).show()

                }

            })
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.iconclient -> {
                startActivity( Intent(this@AddChilli , ClientData::class.java))
            }
            R.id.iconnotify -> {
                startActivity( Intent(this@AddChilli , Notification::class.java))
            }
            R.id.close -> {
                var builder= AlertDialog.Builder(this@AddChilli)
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