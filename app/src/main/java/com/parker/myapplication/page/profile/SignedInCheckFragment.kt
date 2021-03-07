package com.parker.myapplication.page.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.R

class SignedInCheckFragment : Fragment() {

    private lateinit var listener : SignedInCheckListener
    interface SignedInCheckListener {
        fun signedIn()
        fun notSignedIn()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as SignedInCheckListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var currentUser = Firebase.auth.currentUser
        if (currentUser != null) {
            listener.signedIn()
        } else {
            listener.notSignedIn()
        }
        
        return inflater.inflate(R.layout.fragment_signed_in_check, container, false)
    }
}