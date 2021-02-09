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
//onCreate: 앱이 최로로 실행될 때 발동되는 함수 그다음이 onStart/ override private
    //super.:부모클래스 자체를 호출한다(MainActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //현재의 UI를 일단저장해놈
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)
        changeFragment(mainFragment)
    }

    //로그인 버튼 누르면 로그인프레그먼트
    override fun onLoginButtonClick() {
        changeFragment(loginFragment)
    }

    //레지스터 버튼 누르면 레지스터프레그먼트트
   override fun onRegisterButtonClick() {

    }
}
//프레그먼트간 전환을 실행