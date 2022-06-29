package com.example.agrotech

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agrotech.databinding.ActivityMainBinding
import com.example.agrotech.databinding.ActivityPlotsListBinding
import com.example.agrotech.interfaces.PlotService
import com.example.agrotech.models.Plot
import com.example.agrotech.models.PlotContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

class Plots_List : AppCompatActivity() {

    lateinit var binding: ActivityPlotsListBinding
    lateinit var toggle: ActionBarDrawerToggle

    var plots = ArrayList<Plot>()

    var plotAdapter = PlotAdapter(plots)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plots_list)



        //actionBar!!.setDisplayHomeAsUpEnabled(true)

        val plotlist = this





        plotAdapter.onItemClick = {
            val intent = Intent(this, ViewPlot::class.java)
            intent.putExtra("actualplot", it)
            startActivity(intent)
        }

        binding = ActivityPlotsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toggle= ActionBarDrawerToggle(this@Plots_List,drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
            navView.setOnClickListener {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END)
                } else {
                    drawerLayout.openDrawer(GravityCompat.END)
                }
            }
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.f02->{
                        startActivity( Intent(this@Plots_List , AddPlot::class.java))}
                    R.id.f03->{
                        startActivity( Intent(this@Plots_List , ViewPlot::class.java))}
                    R.id.f05->{
                        startActivity( Intent(this@Plots_List , AddChilli::class.java))}
                    R.id.f06->{
                        startActivity( Intent(this@Plots_List , ViewChilli::class.java))}
                    R.id.f07->{
                        startActivity( Intent(this@Plots_List , Season::class.java))}
                    R.id.f08->{
                        startActivity( Intent(this@Plots_List , Treat::class.java))}
                    R.id.f09->{
                        startActivity( Intent(this@Plots_List , Plots_List::class.java))}
                    R.id.f10->{
                        startActivity( Intent(this@Plots_List , MainActivity::class.java))}
                }
                true
            }

        }

        loadPlots()
        initView()

        initView()



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.iconclient -> {
                startActivity( Intent(this@Plots_List , ClientData::class.java))
            }
            R.id.iconnotify -> {
                startActivity( Intent(this@Plots_List , Notification::class.java))
            }

            R.id.close -> {
                var builder= AlertDialog.Builder(this@Plots_List)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bar_icons, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initView() {
        //vinculo el adapter al rV
        val rvPlot = findViewById<RecyclerView>(R.id.rvPlots)
        rvPlot.adapter = plotAdapter
        rvPlot.layoutManager = LinearLayoutManager(this)
    }

    private fun loadPlots() {

        val plotService: PlotService = Retro().getRetroClient().create(PlotService::class.java)
        plotService.getPlots().enqueue(object : Callback<PlotContent> {
            override fun onResponse(call: Call<PlotContent>?, response: Response<PlotContent>?) {

                var plotData = response?.body()?.content
                if (plotData != null) {
                    plots.addAll(plotData)
                    plotAdapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<PlotContent>?, t: Throwable?) {
                Toast.makeText(this@Plots_List, "Error", Toast.LENGTH_LONG).show()
            }

        })

        //var adapter = PlotAdapter(plots)



    //recipes.add(Recipe("Ensalada de Frutas", "Esta ensalada esta tiene diversas frutas como bla bla bla bla bla", "70", "5", "6:00 min"))


    }
}