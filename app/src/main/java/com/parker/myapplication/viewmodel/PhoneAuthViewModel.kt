package com.parker.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthProvider
import com.parker.myapplication.helper.TAG

class PhoneAuthViewModel : ViewModel() {

    lateinit var verificationId: String
    lateinit var token: PhoneAuthProvider.ForceResendingToken

    private fun phoneNumberToKoreanNumber(phoneNumber: String): String {
        var newPhoneNumber = "+82"
        phoneNumber.forEachIndexed { index, ch ->
            if (index != 0) {
                if (index == 3 || index == 7) newPhoneNumber += '-'
                newPhoneNumber += ch
            }
        }
        return newPhoneNumber
    }

    val phoneNumber = MutableLiveData<String>("")
    val userCode = MutableLiveData<String>("")

    val _reqAuthCode = MutableLiveData<Boolean>()
    val _verifyCode = MutableLiveData<Boolean>()

    val reqAuthCode: LiveData<Boolean> get() = _reqAuthCode
    val verifyCode: LiveData<Boolean> get() = _verifyCode

    fun reqVerifyCode(phoneNumber: String) {
        _reqAuthCode.value = phoneNumber.isNotBlank()
    }

    fun isCodeValid() {
        _verifyCode.value = this::verificationId.isInitialized &&
                userCode.value.toString().isNotBlank()
    }
}