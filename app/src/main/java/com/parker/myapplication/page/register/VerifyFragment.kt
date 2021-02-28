package com.parker.myapplication.page.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentVerifyBinding
import com.parker.myapplication.helper.TAG
import com.parker.myapplication.data.UserInfo
import com.parker.myapplication.page.main.AuthenticationBaseFragment
import com.parker.myapplication.viewmodel.PhoneAuthViewModel
import com.parker.myapplication.viewmodel.UserInfoViewModel
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match

class VerifyFragment : AuthenticationBaseFragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentVerifyBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private var verificationId: String? = null
    private var token: PhoneAuthProvider.ForceResendingToken? = null

    //  set activity viewmodels
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private val phoneAuthViewModel: PhoneAuthViewModel by activityViewModels()

    private lateinit var userInfo: UserInfo

    private var listener: EventListener? = null

    interface EventListener {
        fun onVerifyDone()
    }

    private fun dataBinding() {
        binding.authViewModel = phoneAuthViewModel
    }

    private fun enableVerifyCodeView() {
        binding.authenticateNumber.isEnabled = true
        binding.authenticateNumber.setBackgroundResource(R.drawable.box_shape)
    }

    private fun enableVerifyView() {
        binding.registerSignUpButton.isEnabled = true
    }

    //  전화번호 인증 문자를 사용자에게 보냄
    private fun reqCellPhoneAuth(phoneNumber: String) {
        if (phoneNumber == "") {
            Toast.makeText(context, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

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
//            phoneAuthViewModel.setAuthCode(credential.smsCode.toString())
            Toast.makeText(context, "인증 코드를 발송하였습니다. 60초안에 인증코드를 입력해주세요.", Toast.LENGTH_SHORT)
                .show()
            enableVerifyCodeView()
            enableVerifyView()

        }

        override fun onVerificationFailed(firebaseException: FirebaseException) {
            Toast.makeText(
                context,
                "인증 코드를 발생하는데 오류가 발생했습니다. 올바른 전화번호를 입력해주세요.",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            verificationId = p0
            phoneAuthViewModel.verificationId = p0
            token = p1
            Toast.makeText(context, "인증 코드를 발송하였습니다. 60초안에 인증코드를 입력해주세요.", Toast.LENGTH_SHORT)
                .show()
            enableVerifyCodeView()
            enableVerifyView()
        }
    }

    private fun setUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
    }

    private fun getUserInfo() {
        userInfoViewModel.getUserInfo().observe(viewLifecycleOwner, Observer { userinfo ->
            setUserInfo(userinfo)
        })
    }

    private fun setCallBacks() {
        phoneAuthViewModel.reqAuthCode.observe(viewLifecycleOwner, Observer { phoneNumberNotBlank ->
            if (phoneNumberNotBlank) {
                Log.d(TAG, "Phonenumber = ${phoneAuthViewModel.phoneNumber.value.toString()}")
                reqCellPhoneAuth(phoneAuthViewModel.phoneNumber.value!!.toString())
            } else {
                Toast.makeText(context, "핸드폰 번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        })

        phoneAuthViewModel.verifyCode.observe(viewLifecycleOwner, Observer {
            if (it) {
                verifyCode(verificationId, phoneAuthViewModel.userCode.value.toString())
            } else {
                Toast.makeText(context, "인증번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_verify, container, false)
        val view = binding.root
        firebaseAuth = Firebase.auth
        dataBinding()
        getUserInfo()
        setCallBacks()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as EventListener
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    Toast.makeText(context, "회원가입을 환영합니다. 다시 로그인 해주세요.", Toast.LENGTH_SHORT).show()
                    listener!!.onVerifyDone()
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(context, "잘못된 인증코드입니다. 다시 입력해주세요", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun verifyCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

}