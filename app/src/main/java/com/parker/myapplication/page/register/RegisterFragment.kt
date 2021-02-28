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
import androidx.fragment.app.activityViewModels
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.databinding.FragmentRegisterBinding
import com.parker.myapplication.model.UserInfo
import com.parker.myapplication.page.main.AuthenticationBaseFragment
import com.parker.myapplication.viewmodel.UserInfoViewModel
import java.util.concurrent.TimeUnit


class RegisterFragment : AuthenticationBaseFragment(), View.OnClickListener {

    private val TAG: String = "RegisterFragment"
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var signUpButton: Button
    private lateinit var emailView: EditText
    private lateinit var passwordView: EditText
    private lateinit var nameView: EditText
    private lateinit var userInfo: UserInfo

    private var listener: OnRegisterDoneListener? = null

    // set View Models
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()

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

    private fun checkAllAreaFilled(): Boolean {
        return !(emailView.text.equals("") || passwordView.text.equals("") || nameView.text.equals(""))
    }

    private fun setOnClickEvent() {
        signUpButton.setOnClickListener(this)
    }

    public override fun onStart() {
        super.onStart()
    }

    private fun getUserInfo(): UserInfo {
        return UserInfo(
            binding.registerName.text.toString(),
            binding.registerEmail.text.toString(),
            binding.registerPassword.text.toString()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
//        firebaseAuth = Firebase.auth
        setVariables()
        setOnClickEvent()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnRegisterDoneListener
    }

    private fun whenNotFilled() {
        Toast.makeText(context, "모든 입력란을 채워주세요", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            signUpButton.id -> {
                if (checkAllAreaFilled()) {
                    userInfoViewModel.setInfo(getUserInfo())
                    listener!!.onRegisterDone()
                } else {
                    whenNotFilled()
                }
            }
        }
    }
}