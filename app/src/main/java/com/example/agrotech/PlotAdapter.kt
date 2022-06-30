package com.example.agrotech

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agrotech.models.Plot

class PlotAdapter(var plots: ArrayList<Plot>): RecyclerView.Adapter<PlotAdapter.PlotPrototype>() {

    var onItemClick : ((Plot) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlotPrototype {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_plots, parent, false)

        return PlotPrototype(view)
    }

    override fun onBindViewHolder(holder: PlotPrototype, position: Int) {
        val plot = plots[position]
        holder.tvTitle.setText(plot.description)
        holder.tvLocation.setText(plot.location)
        holder.tvArea_info.setText(plot.area.toString())
        holder.tvVolumen_info.setText(plot.volume.toString())

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(plot)
        }



    }

    override fun getItemCount(): Int {
        return plots.size
    }

    class PlotPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //vinculamos los controles del prototype con variables
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitlePlot)
        val tvLocation = itemView.findViewById<TextView>(R.id.tvLocation)
        val tvArea_info = itemView.findViewById<TextView>(R.id.tvArea_info)
        val tvVolumen_info= itemView.findViewById<TextView>(R.id.tvVolumen_info)


    }


}




