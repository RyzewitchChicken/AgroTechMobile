package com.example.agrotech


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.agrotech.interfaces.PlagueService

import com.example.agrotech.models.PlagueContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomAdapter(private val templist: ArrayList<String>,private val humilist: ArrayList<String>,private val nameplist: ArrayList<String> ):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {





    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        viewHolder.temp.text="TEMPERATURA: " + templist[i]
        viewHolder.humi.text="HUMEDAD: " + humilist[i]
        viewHolder.namep.text=  nameplist[i]
    }

    override fun getItemCount(): Int {
        return templist.size

    }

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        var temp:TextView
        var humi:TextView
        var namep:TextView
        init {
            temp=itemView.findViewById(R.id.temp)
            humi=itemView.findViewById(R.id.humi)
            namep=itemView.findViewById(R.id.namep)
        }
    }

}
