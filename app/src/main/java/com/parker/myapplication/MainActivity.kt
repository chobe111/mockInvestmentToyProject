package com.parker.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.parker.myapplication.databinding.ActivityMainBinding
import com.parker.myapplication.page.LoginFragment
import com.parker.myapplication.page.MainFragment
import com.parker.myapplication.page.RegisterFragment
import kotlin.math.log

class MainActivity : AppCompatActivity(), MainFragment.OnButtonClickEvent {

    private lateinit var binding: ActivityMainBinding
    private val mainFragment by lazy { MainFragment() }
    private val loginFragment by lazy { LoginFragment() }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)
        changeFragment(mainFragment)
    }

    override fun onLoginButtonClick() {
        changeFragment(loginFragment)
    }

    override fun onRegisterButtonClick() {

    }
}