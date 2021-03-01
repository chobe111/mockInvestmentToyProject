package com.parker.myapplication

import VerifyFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.parker.myapplication.databinding.ActivityMainBinding
import com.parker.myapplication.page.main.LoginFragment
import com.parker.myapplication.page.main.MainFragment
import com.parker.myapplication.page.register.ExampleFragment
import com.parker.myapplication.page.register.RegisterFragment

class MainActivity : AppCompatActivity(), MainFragment.OnButtonClickEvent, RegisterFragment.OnRegisterFragmentListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val mainFragment by lazy { MainFragment() }
    private val loginFragment by lazy { LoginFragment() }
    private val registerFragment by lazy { RegisterFragment() }
    private val verifyFragment by lazy { VerifyFragment() }

    private val exampleFragment by lazy {ExampleFragment()}


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)/*화면을 보여주는 거, setContentView()안에 넣어야 함*/

        changeFragment(mainFragment)
    }

    override fun onLoginButtonClick() {
        changeFragment(loginFragment)
    }

    override fun onRegisterButtonClick() {
        changeFragment(registerFragment)
    }


    override fun onNextButtonClick() {
        changeFragment(verifyFragment)
    }
    }