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

        // Enlazar el DrawerLayout desde el layout
        drawerLayout = findViewById(R.id.drawer_layout)

        // Configurar la barra de herramientas (Toolbar)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configurar el NavigationView
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // Configurar el ActionBarDrawerToggle para abrir y cerrar el DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_nav, // Descripción de apertura del menú lateral
            R.string.close_nav // Descripción de cierre del menú lateral
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Si savedInstanceState es nulo, reemplazar el fragmento actual con el fragmento HomeFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home) // Marcar el elemento "Inicio" en el menú lateral
            updateToolbarTitle("Inicio") // Actualizar el título de la barra de herramientas
        }

        // Agregar un callback para manejar el botón de retroceso
        onBackPressedDispatcher.addCallback(this, callback)
    }

    // Método que se ejecuta cuando se selecciona un elemento del menú lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Según el ID del elemento seleccionado, realizar una acción
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment()).commit()
            }
            R.id.nav_appointment -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AppointmentFragment()).commit()
            }
            R.id.nav_record -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, RecordFragment()).commit()
            }
            R.id.nav_patients -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, PatientsFragment()).commit()
            }
            R.id.nav_users -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, UsersFragment()).commit()
            }
            R.id.nav_settings -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SettingsFragment()).commit()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START) // Cerrar el menú lateral
        return true
    }

    // Método para mostrar un cuadro de diálogo de alerta

    // Callback personalizado para manejar el botón de retroceso
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                // Si el menú lateral está abierto, ciérralo
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                // Si el menú lateral está cerrado, realiza el comportamiento predeterminado
                onBackPressed() // Llamar al método predeterminado onBackPressed
            }
        }
    }

    // Método para actualizar el título de la barra de herramientas
    fun updateToolbarTitle(title: String) {
        supportActionBar?.title = title
    }
}
