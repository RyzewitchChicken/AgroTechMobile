package com.example.agrotech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.agrotech.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

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
                        startActivity( Intent(this@MainActivity , AddPlot::class.java))}
                    R.id.f03->{
                        startActivity( Intent(this@MainActivity , ViewPlot::class.java))}
                    R.id.f05->{
                        startActivity( Intent(this@MainActivity , AddChilli::class.java))}
                    R.id.f06->{
                        startActivity( Intent(this@MainActivity , ViewChilli::class.java))}
                    R.id.f07->{
                        startActivity( Intent(this@MainActivity , Season::class.java))}
                    R.id.f08->{
                        startActivity( Intent(this@MainActivity , Treat::class.java))}
                }
                true
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.iconclient -> {
                startActivity( Intent(this@MainActivity , ClientData::class.java))
            }
            R.id.iconnotify -> {
                startActivity( Intent(this@MainActivity , Notification::class.java))
            }
        }
        if (toggle.onOptionsItemSelected(item)) {
            true

        }


        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_icons,menu)

        return super.onCreateOptionsMenu(menu)
    }
}