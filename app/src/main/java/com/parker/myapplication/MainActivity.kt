package com.parker.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.databinding.ActivityMainBinding
import com.parker.myapplication.page.LoginFragment
import com.parker.myapplication.page.MainFragment
import com.parker.myapplication.page.RegisterFragment
import kotlin.math.log

class MainActivity : AppCompatActivity(), MainFragment.OnButtonClickEvent, RegisterFragment.OnRegisterDoneListener {

//    private lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val mainFragment by lazy { MainFragment() }
    private val loginFragment by lazy { LoginFragment() }
    private val registerFragment by lazy { RegisterFragment() }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        changeFragment(mainFragment)
    }

    override fun onLoginButtonClick() {
        changeFragment(loginFragment)
    }

    override fun onRegisterButtonClick() {
        changeFragment(registerFragment)
    }

    override fun onRegisterDone() {

    }
}