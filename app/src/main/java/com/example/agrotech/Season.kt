package com.example.agrotech

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agrotech.interfaces.PlagueService
import com.example.agrotech.interfaces.SeasonService
import com.example.agrotech.models.PlagueContent
import com.example.agrotech.models.SeasonContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

class Season:AppCompatActivity() {

    lateinit var descs:String
    lateinit var dateS:String
    lateinit var dateE:String
    lateinit var plagues:String
    var descAr = ArrayList<String>()
    var dateSAr = ArrayList<String>()
    var dateEAr = ArrayList<String>()
    var plaguesAr = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.season)


        val recor=findViewById<ImageView>(R.id.recordatory)


        val simpleDateFormat= SimpleDateFormat("dd,MMM,yyyy, HH:mm")

        val seasonService: SeasonService = Retro().getRetroClient().create(SeasonService::class.java)
        seasonService.getSeason().enqueue(object: Callback<SeasonContent> {
            override fun onResponse(call: Call<SeasonContent>?, response: Response<SeasonContent>?) {
                    try {
                        var seasonData = response?.body()?.content
                        if (seasonData != null) {
                            var i = 0
                            while (i < seasonData.size) {
                                val jsonObject = seasonData.get(i)
                                descs= jsonObject.description.toString()
                                dateS= simpleDateFormat.format(jsonObject.startDate)
                                dateE= simpleDateFormat.format(jsonObject.endDate)
                                i++
                                descAr.add(descs)
                                dateSAr.add(dateS)
                                dateEAr.add(dateE)

                            }

                        }

                    } catch (e: Exception) {

                    }

            }
            override fun onFailure(call: Call<SeasonContent>?, t: Throwable?) {
                Toast.makeText(this@Season,"Error", Toast.LENGTH_LONG).show()
            }


        })
        val plagueService:PlagueService=Retro().getRetroClient().create(PlagueService::class.java)
        plagueService.getPlague().enqueue(object :Callback<PlagueContent> {
            override fun onResponse(call: Call<PlagueContent>?, response: Response<PlagueContent>?) {
                if (response != null) {
                    try {
                        var plagueData = response?.body()?.content
                        if (plagueData != null) {
                            var i = 0
                            while (i < plagueData.size) {
                                val jsonObject = plagueData.get(i)
                                plagues= jsonObject.type.toString()
                                i++
                                plaguesAr.add(plagues)
                                println(plaguesAr)

                            }
                        }

                    } catch (e: Exception) {

                    }
                    val recycler=findViewById<RecyclerView>(R.id.recyclerseason)

                    val adapter=SeasonAdapter(descAr,dateSAr,dateEAr,plaguesAr)
                    recycler.layoutManager= LinearLayoutManager(this@Season)
                    recycler.adapter=adapter
                    Toast.makeText(this@Season,"Presione para activar recordatorios", Toast.LENGTH_LONG).show()
                    adapter.setOnItemClickListener(object: SeasonAdapter.onItemClickListener{
                        override fun onItemClick(i: Int) {
                            val cal = Calendar.getInstance()
                            cal.time = simpleDateFormat.parse(dateSAr[i])
                            Toast.makeText(this@Season,"Recordatorio Activado", Toast.LENGTH_LONG).show()
                            val intent = Intent(Intent.ACTION_INSERT)
                            intent.setData(CalendarContract.Events.CONTENT_URI)
                            intent.putExtra(CalendarContract.Events.TITLE,"Recordatorio de temporada de plaga")
                            intent.putExtra(CalendarContract.Events.DESCRIPTION, descAr[i] + " Posible Plagas  " + plaguesAr[i])

                            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,  cal.timeInMillis)


                            if(intent.resolveActivity(packageManager)!=null){
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@Season,"Error", Toast.LENGTH_LONG).show()
                            }

                        }

                    })
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