package com.parker.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.parker.myapplication.databinding.ActivityMainBinding
import com.parker.myapplication.databinding.ActivityTestBinding
import com.parker.myapplication.viewmodel.EventViewModel
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private val viewModel by lazy { ViewModelProvider(this).get(EventViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_investment, R.id.navigation_market, R.id.navigation_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        viewModel.init(this)
        viewModel.fetchData().observe(this, Observer {
            Log.d("fetchData", "$it")
        })
    }
}