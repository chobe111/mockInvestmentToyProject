package com.parker.myapplication.page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var listener: OnButtonClickEvent

    interface OnButtonClickEvent {
        fun onLoginButtonClick()
        fun onRegisterButtonClick()
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
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        val loginButton = binding.loginButton
        val registerButton = binding.registerButton
        setClickEvent()
        return view
    }

    private fun loginButtonClick() {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnButtonClickEvent
    }
}