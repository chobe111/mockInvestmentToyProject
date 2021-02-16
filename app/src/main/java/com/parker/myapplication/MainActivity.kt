package com.parker.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseUser
import com.parker.myapplication.databinding.ActivityMainBinding
import com.parker.myapplication.page.investment.ProfileFragment
import com.parker.myapplication.page.main.LoginFragment
import com.parker.myapplication.page.main.MainFragment
import com.parker.myapplication.page.register.RegisterFragment
import com.parker.myapplication.page.register.VerifyFragment

class MainActivity : AppCompatActivity(), MainFragment.OnButtonClickEvent,
    RegisterFragment.OnRegisterDoneListener, LoginFragment.EventListener,
    VerifyFragment.EventListener{

//    private lateinit var binding: ActivityMainBinding

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val mainFragment by lazy { MainFragment() }
    private val loginFragment by lazy { LoginFragment() }
    private val registerFragment by lazy { RegisterFragment() }
    private val profileFragment by lazy { ProfileFragment() }
    private val verifyFragment by lazy { VerifyFragment() }

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
        changeFragment(verifyFragment)
    }

    override fun onLoginDone(user: FirebaseUser) {
        changeFragment(profileFragment)
    }

    override fun onVerifyDone() {
        TODO("Not yet implemented")
    }
}