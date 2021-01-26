package com.parker.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.parker.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private fun changeToLoginFragment() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        changeToLoginFragment()
    }
}