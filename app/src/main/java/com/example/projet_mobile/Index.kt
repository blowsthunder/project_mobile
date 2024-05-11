package com.example.projet_mobile

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Index : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_index)

        val drawerLayout :DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayUseLogoEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_nouveau -> Toast.makeText(applicationContext,"Clicked Nouveau",Toast.LENGTH_SHORT).show()
                R.id.nav_modifier1 -> Toast.makeText(applicationContext,"Clicked Modifier de ajouter",Toast.LENGTH_SHORT).show()
                R.id.nav_supprimer -> Toast.makeText(applicationContext,"Clicked Supprimer",Toast.LENGTH_SHORT).show()
                R.id.nav_saisir -> Toast.makeText(applicationContext,"Clicked Saisir",Toast.LENGTH_SHORT).show()
                R.id.nav_modifier2 -> Toast.makeText(applicationContext,"Clicked Modifier de Notes",Toast.LENGTH_SHORT).show()
                R.id.nav_par_module -> Toast.makeText(applicationContext,"Clicked par module",Toast.LENGTH_SHORT).show()
                R.id.nav_Delibiration -> Toast.makeText(applicationContext,"Clicked Delibiration",Toast.LENGTH_SHORT).show()
                R.id.nav_logout -> Toast.makeText(applicationContext,"Clicked LogOut",Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}