package com.parker.myapplication.page.oauth2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentFaceBookRegisterBinding
import com.parker.myapplication.helper.TAG

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class FaceBookRegisterFragment : Fragment() {

    private lateinit var binding: FragmentFaceBookRegisterBinding
    private lateinit var faceBookButton: LoginButton
    private lateinit var callbackManager: CallbackManager

    private val callback = object : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult?) {
            Log.d(TAG, "onSuccess: ${result}")
        }

        override fun onCancel() {
            Log.d(TAG, "onCancel: ")
        }

        override fun onError(error: FacebookException?) {
            Log.d(TAG, "onError: ${error}")
        }
    }

    private fun setViews() {
        faceBookButton = binding.facebookLoginButton
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setFaceBookLoginCallbacks() {
        callbackManager = CallbackManager.Factory.create()
        faceBookButton.setReadPermissions("email")
        faceBookButton.fragment = this
        faceBookButton.registerCallback(callbackManager, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_face_book_register,
            container,
            false
        )

        val view = binding.root
        setViews()
        setFaceBookLoginCallbacks()
        return view
    }
}