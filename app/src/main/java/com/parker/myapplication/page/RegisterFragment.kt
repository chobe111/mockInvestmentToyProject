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
import com.parker.myapplication.databinding.FragmentLoginBinding
import com.parker.myapplication.databinding.FragmentRegisterBinding
import kotlin.math.sign


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

    private fun setOnClickListener() {
        signUpButton.setOnClickListener(this)
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE;
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE;
    }

    //  TODO: 데이터 베이스에 사용자 정보 추가.
    private fun addUserOnDB() {

    }

    //  TODO: 데이터 베이스에 사용자 정보 추가.
    private fun onAuthSuccess() {

    }

    //  TODO: 아직 작성할 필요 없음
    private fun reqCellPhoneAuth() {

    }

    private fun sendMessageToUser() {

    }

    private fun onCellPhoneResDone() {

    }

    private fun createAccount(email: String, password: String) {
        showProgressBar()
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            hideProgressBar()
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser
                Toast.makeText(context, "회원가입을 환영합니다.", Toast.LENGTH_SHORT).show()
//              TODO: UpdateUI to Investment Screen
            } else {
                Log.d(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        }
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
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
        when (v!!.id) {
            signUpButton.id -> {
                createAccount(emailView.text.toString(), passwordView.text.toString())
            }
        }
    }
}