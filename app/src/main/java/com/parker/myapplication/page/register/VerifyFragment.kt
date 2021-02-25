package com.parker.myapplication.page.register

import android.content.Context
import android.graphics.drawable.Drawable
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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentMainBinding
import com.parker.myapplication.databinding.FragmentVerifyBinding
import com.parker.myapplication.helper.TAG
import com.parker.myapplication.model.UserInfo
import com.parker.myapplication.page.main.AuthenticationBaseFragment
import com.parker.myapplication.viewmodel.UserInfoViewModel
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match

class VerifyFragment : AuthenticationBaseFragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentVerifyBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var phoneNumberView: EditText
    private lateinit var verifyCodeView: EditText
    private lateinit var reqCodeButton: Button
    private lateinit var reqVerifyButton: Button
    private lateinit var registerSignUpButton: Button

    private var verificationId: String? = null
    private var token: PhoneAuthProvider.ForceResendingToken? = null

    //  set activity viewmodels
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var userInfo: UserInfo

    private var listener: EventListener? = null

    interface EventListener {
        fun onVerifyDone(firebaseUser: FirebaseUser)
    }

    //  TODO: 데이터 베이스에 사용자 정보 추가.
    private fun addUserOnDB() {

    }

    //  TODO: 데이터 베이스에 사용자 정보 추가.
    private fun onAuthSuccess() {

    }

    private fun setVariables() {
        verifyCodeView = binding.authenticateNumber
        phoneNumberView = binding.phoneNumber
        reqVerifyButton = binding.verifyRequestButton
        setProgressBar(binding.registerProgressBar)
//      TODO: Integrate ClickListener
        reqVerifyButton.setOnClickListener {
            reqCellPhoneAuth()
            verifyCodeView.setBackgroundResource(R.drawable.box_shape)
        }
        registerSignUpButton.setOnClickListener {
            val code: String = verifyCodeView.text.toString()
            verifyCode(verificationId, code)
        }
        registerSignUpButton = binding.registerSignUpButton



        verifyCodeView.isClickable = false
    }

    private fun createAccount(email: String, password: String) {
        showProgressBar()
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            hideProgressBar()
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser
                Toast.makeText(context, "회원가입을 환영합니다. 다시 로그인 해주세요", Toast.LENGTH_SHORT).show()
                listener!!.onVerifyDone(user!!)
            } else {
                Log.d(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun makePhoneNumber(basePhoneNumber: String): String {
        return basePhoneNumber
    }


    //  전화번호 인증 문자를 사용자에게 보냄
    private fun reqCellPhoneAuth() {
        var phoneNumber = phoneNumberView.text.toString()
        if (phoneNumber == "") {
            Toast.makeText(context, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        phoneNumber = makePhoneNumber(phoneNumber)

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            TODO("Not yet implemented")
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            TODO("Not yet implemented")
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            this@VerifyFragment.verificationId = verificationId
            this@VerifyFragment.token = token
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "올바른 코드입니다.", Toast.LENGTH_SHORT)
                    val user = task.result?.user
                    createAccount(userInfo.email, userInfo.password)
                } else {
                    // Sign in failed, display a message and update the UI
                    Toast.makeText(context, "잘못된 코드입니다. 다시 입력해주세요", Toast.LENGTH_SHORT).show()
                    Log.w(TAG, "잘못된 코드입니다. 다시 입력해주세요", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    }
                }
            }
    }


    private fun verifyCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun setUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
    }

    private fun getUserInfo() {
        userInfoViewModel.getUserInfo().observe(viewLifecycleOwner, Observer { userinfo ->
            setUserInfo(userinfo)
            Log.d(TAG, "getUserInfo: SetUserInfoDone")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerifyBinding.inflate(inflater, container, false)
        val view = binding.root
        firebaseAuth = Firebase.auth
        setVariables()
        getUserInfo()
        return view

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as EventListener
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            reqVerifyButton.id -> {

            }
            registerSignUpButton.id -> {

            }
        }
    }
}