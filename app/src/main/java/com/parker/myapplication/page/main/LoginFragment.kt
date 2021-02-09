package com.parker.myapplication.page.main

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.databinding.FragmentLoginBinding
import com.parker.myapplication.helper.TAG


class LoginFragment : AuthenticationBaseFragment(), View.OnClickListener {

    //  TODO: 비밀번호 기반 사용자 인증 기능 구현
//
    //  전역 변수 정의
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding
    private lateinit var signUpButton: Button
    private lateinit var emailView: EditText
    private lateinit var passwordView: EditText

    private var listener: EventListener? = null

    interface EventListener {
        fun onLoginDone(user: FirebaseUser)
    }

    //  TODO:
    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            return
        }
        listener!!.onLoginDone(user)
    }

    private fun validateForm(): Boolean {
        var valid = true
        val email = binding.loginFragmentId.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.loginFragmentId.error = "Required."
            valid = false
        } else {
            binding.loginFragmentId.error = null
        }

        val password = binding.loginFragmentPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.loginFragmentPassword.error = "Required."
            valid = false
        } else {
            binding.loginFragmentPassword.error = null
        }
        return valid
    }


    private fun onSignInSuccess() {
        Log.d(TAG, "signInWithEmail:success")
        val user = auth.currentUser
        updateUI(user)
    }

    private fun onSignInFailed(task: Task<AuthResult>) {
        Log.w(TAG, "signInWithEmail:failure", task.exception)
        Toast.makeText(
            context, "Authentication failed.",
            Toast.LENGTH_SHORT
        ).show()
        updateUI(null)
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }

        showProgressBar()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val t = task
                if (task.isSuccessful) {
                    onSignInSuccess()
                } else {
                    onSignInFailed(task)
                }
                hideProgressBar()
            }
    }

    private fun setViewVariables() {
        signUpButton = binding.loginFragmentLoginButton
        emailView = binding.loginFragmentId
        passwordView = binding.loginFragmentPassword
//        setProgressBar(binding)
    }

    private fun setOnClickEvent() {
        signUpButton.setOnClickListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = Firebase.auth
        setViewVariables()
        setOnClickEvent()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as EventListener
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        when (v!!.id) {
            signUpButton.id -> {
                signIn(emailView.text.toString(), passwordView.text.toString())
            }
        }
    }

}