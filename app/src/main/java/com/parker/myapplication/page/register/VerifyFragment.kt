package com.parker.myapplication.page.register

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
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
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentMainBinding
import com.parker.myapplication.databinding.FragmentVerifyBinding
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match

class VerifyFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentVerifyBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var phoneNumberView: EditText
    private lateinit var verifyCodeView: EditText
    private lateinit var reqCodeButton: Button

    private var verificationId: String? = null
    private var token: PhoneAuthProvider.ForceResendingToken? = null


    private var listener: EventListener? = null

    interface EventListener {
        fun onVerifyDone()
    }

    //  TODO: 데이터 베이스에 사용자 정보 추가.
    private fun addUserOnDB() {

    }

    //  TODO: 데이터 베이스에 사용자 정보 추가.
    private fun onAuthSuccess() {

    }

    //  TODO: 아직 작성할 필요 없음
    private fun reqCellPhoneAuth() {
        val phoneNumber = phoneNumberView.text.toString()
        if (phoneNumber == "") {
            Toast.makeText(context, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity!!)
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerifyBinding.inflate(inflater, container, false)
        val view = binding.root

        return view

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as EventListener
    }
}