package com.parker.myapplication.page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentLoginBinding
import com.parker.myapplication.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var  binding:FragmentMainBinding
    private lateinit var listener: OnButtonClickEvent

    interface OnButtonClickEvent {
        fun onLoginButtonClick()
        fun onRegisterButtonClick()
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            return
        }
    }

    override fun onStart() {
        super.onStart()
//      현재 유저 정보 가져옴
    }

    private fun setClickEvent() {
        val loginButton = binding.loginButton
        val registerButton = binding.registerButton

        loginButton.setOnClickListener {
            listener.onLoginButtonClick()
        }

        registerButton.setOnClickListener {
            listener.onRegisterButtonClick()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        setClickEvent()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnButtonClickEvent
    }

}