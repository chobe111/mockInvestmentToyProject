package com.parker.myapplication.page

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentRegisterBinding
import java.security.AuthProvider
// 2/9 밑에 코드는 파이어 베이스 회원가입 코드인 거 같은데 못해와서 명기 코드 보고 침.
    class RegisterFragment : Fragment(), View.OnClickListener {


        private val TAG: String = "RegisterFragment"
        private lateinit var binding: FragmentRegisterBinding
        private lateinit var firebaseAuth: FirebaseAuth
        private lateinit var signUpButton: Button
        private lateinit var emailView: EditText
        private lateinit var passwordView: EditText
        private lateinit var nameView: EditText
        private lateinit var progressBar: ProgressBar

        private var listener: OnRegisterDoneListener? = null

        interface OnRegisterDoneListener {
            fun onRegisterDone()
        }

        private fun setVariables() {
            signUpButton = binding.registerSignUpButton
            emailView = binding.registerEmail
            passwordView = binding.registerPassword
            nameView = binding.registerName
            progressBar = binding.registerProgressBar
        }
// 2/9 위에 setvariables 함수 안의 변수들은 모두 fragment_register.xml의 요소들의 id

        private fun setOnClickListener() {
            signUpButton.setOnClickListener(this)
        }

        private fun showProgressBar() {
            progressBar.visibility = View.VISIBLE
        }

        private fun hideProgressBar() {
            progressBar.visibility = View.INVISIBLE
        }

        // TODO: 데이터 베이스에 사용자 정보 추가
        private fun addUserOnDB() {

        }

        private fun createAccount(email: String, password: String) {
            showProgressBar()
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                hideProgressBar()
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Toast.makeText(context, "회원가입을 환영합니다.", Toast.LENGTH_SHORT).show()
                    // TODO: UpdateUI to Investment Screen
                } else {
                    Log.d(TAG, "creatUserWithEmail:failing", task.exception)
                    Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
                    // 2/9 회원가입을 했을 때 여기 나오는 Authentication failed가 떴었는데 아래 run에서 로그들을 보니까
                    //om.google.firebase.auth.FirebaseAuthWeakPasswordException: The given password is
                    // invalid. [ Password should be at least 6 characters ] 이런 로그 떴었는데 비번을 6자리 이상으로 하라는 거였음.
                }
            }
        }

        public override fun onStart() {
            super.onStart()
            val currentUser = firebaseAuth.currentUser
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            binding = FragmentRegisterBinding.inflate(inflater, container, false)
            val view = binding.root
            firebaseAuth = Firebase.auth
            setVariables()
            setOnClickListener()
            return view
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            listener = context as OnRegisterDoneListener
        }


        override fun onClick(v: View?) {
            // TODO("Not yet implemented")
            when (v!!.id) {
                signUpButton.id -> {
                    createAccount(emailView.text.toString(), passwordView.text.toString())
                }
            }
        }
    }