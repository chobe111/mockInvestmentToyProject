package com.parker.myapplication.page.oauth2

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.kakao.sdk.user.UserApiClient
import com.parker.myapplication.MainActivity
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentFaceBookRegisterBinding
import com.parker.myapplication.helper.TAG
import kotlinx.android.synthetic.main.fragment_face_book_register.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class FaceBookRegisterFragment : Fragment() {

    private lateinit var binding: FragmentFaceBookRegisterBinding
    private lateinit var faceBookButton: LoginButton
    private lateinit var callbackManager: CallbackManager

    private val callback = object : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult?) {
//           TODO: Get User Token
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
        //카카오 로그인 및 회원정보 테스트용
        UserApiClient.instance.me { user, error ->
            username.text = "이름: ${user?.kakaoAccount?.profile?.nickname}"
            Glide.with(this).load(user?.kakaoAccount?.profile?.profileImageUrl).into(profileimage_url)
        }
        val view = binding.root
        setViews()
        setFaceBookLoginCallbacks()
        return view
    }
}