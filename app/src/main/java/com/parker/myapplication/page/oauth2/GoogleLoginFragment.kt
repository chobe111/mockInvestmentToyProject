package com.parker.myapplication.page.oauth2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.parker.myapplication.MainActivity
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentGoogleLoginBinding
import com.parker.myapplication.helper.TAG

// TODO: Rename parameter arguments, choose names that match

class GoogleLoginFragment : Fragment() {

    private lateinit var binding: FragmentGoogleLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var googleLoginFragmentEmail: EditText
    private lateinit var googleLoginFragmentPassword: EditText
    private lateinit var googleLoginFragmentLoginButton: Button

    private lateinit var mContext: Context

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    private fun setVariables() {
        googleLoginFragmentEmail = binding.googleLoginFragmentEmail
        googleLoginFragmentLoginButton = binding.googleLoginFragmentLoginButton
        googleLoginFragmentPassword = binding.googleLoginFragmentPassword
        auth = FirebaseAuth.getInstance()
        googleSignInClient = GoogleSignIn.getClient(mContext, gso)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mContext = context
        }
    }

    private fun setOnClickListener() {
        googleLoginFragmentLoginButton.setOnClickListener {
            signIn()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGoogleLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        setVariables()
        setOnClickListener()
        return view
    }

    // Configure Google Sign In


    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
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

    companion object {
        val RC_SIGN_IN = 1234
    }

}