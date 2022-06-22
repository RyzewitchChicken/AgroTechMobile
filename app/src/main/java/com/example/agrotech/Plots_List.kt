package com.example.agrotech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agrotech.interfaces.PlotService
import com.example.agrotech.models.Plot
import com.example.agrotech.models.PlotContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Plots_List : AppCompatActivity() {

    var plots = ArrayList<Plot>()

    var plotAdapter = PlotAdapter(plots)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plots_list)

        val actionBar = supportActionBar

        actionBar!!.title = "PARCELAS"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val viewplot = this



        loadPlots()
        initView()

        initView()
        plotAdapter.setOnItemClickListener(object : PlotAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(viewplot, ViewPlot::class.java)
                startActivity(intent)
            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bar_icons, menu)
        return true
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