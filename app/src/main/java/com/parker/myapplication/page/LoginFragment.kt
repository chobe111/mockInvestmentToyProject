package com.parker.myapplication.page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentLoginBinding
import com.parker.myapplication.databinding.FragmentMainBinding


class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {

            return
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = Firebase.auth
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

}