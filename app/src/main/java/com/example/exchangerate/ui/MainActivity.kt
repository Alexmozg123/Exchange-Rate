package com.example.exchangerate.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.exchangerate.R
import com.example.exchangerate.di.AppComponent
import com.example.exchangerate.di.DaggerAppComponent

class MainActivity : AppCompatActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerAppComponent.create()
        setContentView(R.layout.activity_main)

        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.tbMain)
            .setupWithNavController(navController, appBarConfiguration)
    }
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is MainActivity -> appComponent
        else -> this.applicationContext.appComponent
    }