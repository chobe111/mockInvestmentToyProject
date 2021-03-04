package com.parker.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.facebook.FacebookActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseUser
import com.parker.myapplication.databinding.ActivityMainBinding
import com.parker.myapplication.page.investment.InvestmentFragment
import com.parker.myapplication.page.market.MarketFragment
import com.parker.myapplication.page.profile.ProfileFragment
import com.parker.myapplication.page.main.LoginFragment
import com.parker.myapplication.page.main.MainFragment
import com.parker.myapplication.page.oauth2.FaceBookRegisterFragment
import com.parker.myapplication.page.register.ExampleFragment
import com.parker.myapplication.page.register.RegisterFragment
import com.parker.myapplication.page.register.VerifyFragment
import com.parker.myapplication.viewmodel.UserInfoViewModel

class MainActivity : FragmentActivity(), MainFragment.OnButtonClickEvent,
    RegisterFragment.OnRegisterDoneListener, LoginFragment.EventListener,
    VerifyFragment.EventListener {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
    private val matchMap: Map<Int, Fragment> = mapOf(
        R.id.investment_page to InvestmentFragment(),
        R.id.market_page to MarketFragment(),
        R.id.profile_page to FaceBookRegisterFragment()
    )
    private lateinit var binding: ActivityMainBinding

    private val mainFragment by lazy { MainFragment() }
    private val loginFragment by lazy { LoginFragment() }
    private val registerFragment by lazy { RegisterFragment() }
    private val profileFragment by lazy { ProfileFragment() }

    private val verifyFragment by lazy { VerifyFragment() }
    private val userInfoViewModel: UserInfoViewModel by viewModels()
    private val exampleFragment by lazy { ExampleFragment() }

    private val investmentFragment by lazy { InvestmentFragment() }
    private val marketFragment by lazy { MarketFragment() }



    private lateinit var bottomNavigationView: BottomNavigationView

    private fun setBottomNavigationBarListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            changeFragment(matchMap[it.itemId]!!)
            true
        }
    }

    private fun setVariables() {
        bottomNavigationView = binding.bottomNavigationBar
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setVariables()
        setBottomNavigationBarListener()
        setContentView(view)
        changeFragment(investmentFragment)
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
        changeFragment(mainFragment)
    }
}