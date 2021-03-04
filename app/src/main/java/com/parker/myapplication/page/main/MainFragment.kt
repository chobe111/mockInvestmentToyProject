package com.parker.myapplication.page.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentMainBinding
import com.parker.myapplication.helper.TAG

class MainFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var binding:FragmentMainBinding
    private lateinit var listener: OnButtonClickEvent
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var mContext: Context

    interface OnButtonClickEvent {
        fun onLoginButtonClick()
        fun onRegisterButtonClick()
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            return
        }
    }

    private fun setVariables() {
        auth = FirebaseAuth.getInstance()

    }

    override fun onStart() {
        super.onStart()
    }

    private val RC_SIGN_IN = 9001

    private fun setClickEvent() {
        val loginButton = binding.loginButton
        val registerButton = binding.registerButton
        val googleSignInButton = binding.googleSignInButton
        loginButton.setOnClickListener {
            listener.onLoginButtonClick()
        }

        registerButton.setOnClickListener {
            listener.onRegisterButtonClick()
        }

        googleSignInButton.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        setVariables()
        setClickEvent()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnButtonClickEvent
//        mContext = context
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // ...
            }
        }


    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    // ...
                    Toast.makeText(context, "Authentication Failed.", Toast.LENGTH_SHORT).show()
//                    updateUI(null)
                }

                // ...
            }
    }

}