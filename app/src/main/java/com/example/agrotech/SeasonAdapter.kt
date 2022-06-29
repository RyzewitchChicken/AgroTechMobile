package com.example.agrotech

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Array
import java.text.SimpleDateFormat

class SeasonAdapter(private val desc: ArrayList<String>,private val dateS: ArrayList<String>,private val dateE: ArrayList<String>,private val plague: ArrayList<String>):RecyclerView.Adapter<SeasonAdapter.ViewHolder>() {

    private lateinit var mlistener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(i:Int)

    }

    fun setOnItemClickListener(listener:onItemClickListener){
        mlistener=listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(viewGroup.context).inflate(R.layout.seasonview,viewGroup,false)
        return ViewHolder(v,mlistener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        viewHolder.seasondesc.text= desc[i]
        viewHolder.seasondateS.text= dateS[i]
        viewHolder.seasondateE.text=  dateE[i]
        viewHolder.plague.text=  plague[i]
        println(i)



    }

    override fun getItemCount(): Int {
        return desc.size

    }

    inner class ViewHolder(itemView: View,listener:onItemClickListener): RecyclerView.ViewHolder(itemView) {
        var seasondesc: TextView
        var seasondateS: TextView
        var seasondateE: TextView
        var plague: TextView
        var record:ImageView

        init {
            seasondesc=itemView.findViewById(R.id.seasondesc)
            seasondateS=itemView.findViewById(R.id.seasonDateS)
            seasondateE=itemView.findViewById(R.id.seasonDateE)
            plague=itemView.findViewById(R.id.plague)
            record=itemView.findViewById(R.id.recordatory)
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }
    }

}