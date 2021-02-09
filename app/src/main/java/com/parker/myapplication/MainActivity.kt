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

    // private lateinit var binding: ActivityMainBinding

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!! // !!는 null이 아니다. 무조건 값이 있음을 보장.

    private val mainFragment by lazy { MainFragment() }
    private val loginFragment : LoginFragment by lazy { LoginFragment() }
    private val registerFragment : RegisterFragment by lazy { RegisterFragment() }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) { //여기서 시작
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) //UI넣어주는곳

        changeFragment(mainFragment) //파라미터로 화면 전환
    }

    override fun onLoginButtonClick() {
        changeFragment((loginFragment))
    }

    override fun onRegisterButtonClick() {
        changeFragment((registerFragment))
    }
}