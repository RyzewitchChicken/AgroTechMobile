package com.example.agrotech

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class ViewChilli:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_chilli)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.iconclient -> {
                startActivity( Intent(this@ViewChilli , ClientData::class.java))
            }
            R.id.iconnotify -> {
                startActivity( Intent(this@ViewChilli , Notification::class.java))
            }

            R.id.close -> {
                var builder= AlertDialog.Builder(this@ViewChilli)
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