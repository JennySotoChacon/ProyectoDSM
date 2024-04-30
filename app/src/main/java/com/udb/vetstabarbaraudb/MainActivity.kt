package com.udb.vetstabarbaraudb


import android.app.AlertDialog
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_nav,
            R.string.close_nav
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
            updateToolbarTitle("Inicio")
        }

        onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                showAlertDialog("home")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment()).commit()
            }

            R.id.nav_appointment -> {
                showAlertDialog("appointment")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AppointmentFragment()).commit()
            }

            R.id.nav_record -> {
                showAlertDialog("record")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, RecordFragment()).commit()
            }

            R.id.nav_patients -> {
                showAlertDialog("patients")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, PatientsFragment()).commit()
            }

            R.id.nav_users -> {
                showAlertDialog("users")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, UsersFragment()).commit()
            }

            R.id.nav_settings -> {
                showAlertDialog("settings")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SettingsFragment()).commit()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }


    private fun showAlertDialog(title: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.apply {
            setTitle("Alerta")
            setMessage("Has seleccionado $title")
            setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                // Si el cajón de navegación está abierto, ciérralo
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                // Si el cajón de navegación está cerrado, realiza el comportamiento predeterminado
                onBackPressed() // Llamar al método predeterminado de onBackPressed
            }
        }
    }

    fun updateToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

}