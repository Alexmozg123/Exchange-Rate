package com.example.exchangerate.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.exchangerate.R
import com.example.exchangerate.data.repository.RepositoryImpl
import com.example.exchangerate.domain.Calculator
import com.example.exchangerate.domain.ExtensionsWorker
import com.example.exchangerate.presentation.detailscreen.DetailViewModel
import com.example.exchangerate.presentation.quotationlistscreen.QuotationListViewModel

class MainActivity : AppCompatActivity() {

    private val repository = RepositoryImpl()
    private val calculator = Calculator()
    private val extensionsWorker = ExtensionsWorker()

    private lateinit var quotationListViewModel: QuotationListViewModel
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModels()
        initNavigation()
    }

    private fun initViewModels() {
        quotationListViewModel = ViewModelProvider(this,
            QuotationListViewModel.QuotationListVMFactory(repository, extensionsWorker)
        )[QuotationListViewModel::class.java]

        detailViewModel = ViewModelProvider(this,
            DetailViewModel.DetailVMFactory(calculator)
        )[DetailViewModel::class.java]
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