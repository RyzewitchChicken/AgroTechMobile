package com.example.agrotech

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agrotech.models.Plot

class PlotAdapter(var plots: ArrayList<Plot>): RecyclerView.Adapter<PlotPrototype>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlotPrototype {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_plots, parent, false)

        return PlotPrototype(view, mListener)
    }

    override fun onBindViewHolder(holder: PlotPrototype, position: Int) {
        holder.bind(plots.get(position))
    }

    override fun getItemCount(): Int {
        return plots.size
    }




}

class PlotPrototype(itemView: View, listener: PlotAdapter.onItemClickListener) : RecyclerView.ViewHolder(itemView) {

    //vinculamos los controles del prototype con variables
    val tvTitle = itemView.findViewById<TextView>(R.id.tvTitlePlot)
    val tvLocation = itemView.findViewById<TextView>(R.id.tvLocation)
    val tvArea_info = itemView.findViewById<TextView>(R.id.tvArea_info)
    val tvVolumen_info= itemView.findViewById<TextView>(R.id.tvVolumen_info)

    //vinculamos las variables con la clase
    fun bind(plot: Plot){
        tvTitle.text = plot.name
        tvLocation.text = plot.location
        tvArea_info.text = plot.area.toString()
        tvVolumen_info.text = plot.volume.toString()
    }

    init {
        itemView.setOnClickListener{
            listener.onItemClick(adapterPosition)
        }
    }
}