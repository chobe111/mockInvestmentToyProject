package com.parker.myapplication.page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentLoginBinding
import com.parker.myapplication.databinding.FragmentMainBinding


class LoginFragment : Fragment() {

//  TODO: 비밀번호 기반 사용자 인증 기능 구현
//

    //  전역 변수 정의
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding
    private lateinit var signUpButton: Button
    private lateinit var emailView: EditText
    private lateinit var passwordView: EditText
    private lateinit var nameView: EditText


    //  TODO:
    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            return
        }
    }

    private fun createAccount(email: String, password: String) {

    }

    private fun setViewVariables() {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = Firebase.auth


        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

}