package com.parker.myapplication.helper

import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

object PhoneCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
    override fun onVerificationCompleted(p1: PhoneAuthCredential) {
        TODO("Not yet implemented")
    }

    override fun onVerificationFailed(p0: FirebaseException) {
        TODO("Not yet implemented")
    }
}