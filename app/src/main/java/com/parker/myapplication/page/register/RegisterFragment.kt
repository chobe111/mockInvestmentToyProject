package com.parker.myapplication.page.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.databinding.FragmentRegisterBinding
import com.parker.myapplication.page.main.AuthenticationBaseFragment
import java.util.concurrent.TimeUnit


class RegisterFragment : AuthenticationBaseFragment(), View.OnClickListener {


    private val TAG: String = "RegisterFragment"
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var signUpButton: Button
    private lateinit var emailView: EditText
    private lateinit var passwordView: EditText
    private lateinit var nameView: EditText

    private var listener: OnRegisterDoneListener? = null


    interface OnRegisterDoneListener {
        fun onRegisterDone()
    }

    private fun setVariables() {
        signUpButton = binding.registerSignUpButton
        emailView = binding.registerEmail
        passwordView = binding.registerPassword
        nameView = binding.registerName
        setProgressBar(binding.registerProgressBar)
    }

    private fun setOnClickEvent() {
        signUpButton.setOnClickListener(this)
    }


    private fun createAccount(email: String, password: String) {
        showProgressBar()
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            hideProgressBar()
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser
                Toast.makeText(context, "회원가입을 환영합니다. 다시 로그인 해주세요", Toast.LENGTH_SHORT).show()
                listener!!.onRegisterDone()
            } else {
                Log.d(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    public override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        firebaseAuth = Firebase.auth
        setVariables()
        setOnClickEvent()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnRegisterDoneListener
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            signUpButton.id -> {
                listener!!.onRegisterDone()
            }
        }
    }
}