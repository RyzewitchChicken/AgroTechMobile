package com.example.agrotech

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agrotech.interfaces.PlagueService
import com.example.agrotech.interfaces.SeasonService
import com.example.agrotech.interfaces.UserService
import com.example.agrotech.models.PlagueContent
import com.example.agrotech.models.SeasonContent
import com.example.agrotech.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

class Season:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.season)

        val seasonDesc=findViewById<TextView>(R.id.seasondesc)
        val seasonDates=findViewById<TextView>(R.id.seasonDateS)
        val seasonDatee=findViewById<TextView>(R.id.seasonDateE)
        val plag=findViewById<TextView>(R.id.plague)

        val simpleDateFormat=SimpleDateFormat("EEEE, dd-MMM-yyyy")

        val seasonService: SeasonService = Retro().getRetroClient().create(SeasonService::class.java)
        seasonService.getSeason().enqueue(object: Callback<SeasonContent> {
            override fun onResponse(call: Call<SeasonContent>?, response: Response<SeasonContent>?) {
                if (response != null) {
                    seasonDesc.setText(response.body().content?.get(0)?.description.toString())
                    val datetime=simpleDateFormat.format(response.body().content?.get(0)?.startDate)
                    val datetime2=simpleDateFormat.format(response.body().content?.get(0)?.endDate)
                    seasonDates.text=datetime
                    seasonDatee.text=datetime2

                }
            }

            override fun onFailure(call: Call<SeasonContent>?, t: Throwable?) {
                Toast.makeText(this@Season,"Error", Toast.LENGTH_LONG).show()
            }

        })
        val plagueService: PlagueService = Retro().getRetroClient().create(PlagueService::class.java)
        plagueService.getPlague().enqueue(object: Callback<PlagueContent> {
            override fun onResponse(call: Call<PlagueContent>?, response: Response<PlagueContent>?) {
                if (response != null) {
                    plag.text=response.body().content?.get(0)?.type.toString()
                }
            }

            override fun onFailure(call: Call<PlagueContent>?, t: Throwable?) {
                Toast.makeText(this@Season,"Error", Toast.LENGTH_LONG).show()
            }


        })


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.iconclient -> {
                startActivity( Intent(this@Season , ClientData::class.java))
            }
            R.id.iconnotify -> {
                startActivity( Intent(this@Season , Notification::class.java))
            }

            R.id.close -> {
                var builder= AlertDialog.Builder(this@Season)
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