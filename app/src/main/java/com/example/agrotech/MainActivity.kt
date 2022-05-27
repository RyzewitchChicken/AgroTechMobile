package com.example.agrotech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.agrotech.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            toggle= ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.f01->{
                        startActivity( Intent(this@MainActivity , AddPlot::class.java))}
                    R.id.f02->{
                        startActivity( Intent(this@MainActivity , ClientData::class.java))}
                    R.id.f03->{
                        Toast.makeText(this@MainActivity,"Third item",
                        Toast.LENGTH_LONG).show()}
                    R.id.f04->{
                        Toast.makeText(this@MainActivity,"Fourth item",
                        Toast.LENGTH_LONG).show()}
                    R.id.f05->{
                        Toast.makeText(this@MainActivity,"Fifth item",
                        Toast.LENGTH_LONG).show()}
                }
                true
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }
}