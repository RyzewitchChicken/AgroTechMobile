package com.example.agrotech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class Spinner : AppCompatActivity() {

    lateinit var option: Spinner
    lateinit var nombreaji: TextView
    lateinit var nombrecient: TextView
    lateinit var region: TextView
    lateinit var info: TextView
    var thisspinner = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        option = findViewById(R.id.spinner1)
        nombreaji = findViewById(R.id.tvNombreAji)
        nombrecient = findViewById(R.id.tvNombreCient)
        region = findViewById(R.id.tvRegiones)
        info = findViewById(R.id.tvInfoAji)

        var options = arrayOf("Aji Limo", "Aji Panca", "Aji Verde", "Aji Rocoto")
        var nombres_cientificos = arrayOf("C. chinense", "C. chinense", "C. baccatum", "C. Pubescens")
        var regiones = arrayOf("Costa Norte", "Costa central y sur", "Norte", "Andes bajos")

        option.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                nombreaji.text = options.get(position)
                nombrecient.text = nombres_cientificos.get(position)
                region.text = regiones.get(position)
                if (position == 0){
                    info.text = thisspinner.getString(R.string.ajiLimo)
                }else if (position == 1){
                    info.text = thisspinner.getString(R.string.ajiPanca)
                }else if (position == 2){
                    info.text = thisspinner.getString(R.string.ajiVerde)
                }else if (position == 3){
                    info.text = thisspinner.getString(R.string.ajiRocoto)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                nombreaji.text = "Please select an option"
            }
        }

    }
}