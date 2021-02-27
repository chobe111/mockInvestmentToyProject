package com.parker.myapplication.page.register

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.databinding.FragmentRegisterBinding
import com.parker.myapplication.page.model.UserInfo
import com.parker.myapplication.page.viewmodel.UserInfoViewModel


class RegisterFragment : Fragment(), View.OnClickListener {


    private val TAG: String = "RegisterFragment"
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var emailView: EditText
    private lateinit var passwordView: EditText
    private lateinit var nameView: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var nextButton: Button
    private val userInfoViewModel : UserInfoViewModel by activityViewModels()



    private var listener: OnRegisterFragmentListener? = null

    interface OnRegisterFragmentListener {
        fun onNextButtonClick()
    }
    interface OnTextChangedListener {
        fun afterTextChanged()
        fun beforeTextChanged()
        fun onTextChanged()
    }


    private fun setVariables() {
        nextButton = binding.registerNext
        emailView = binding.registerEmail
        passwordView = binding.registerPassword
        nameView = binding.registerName
        progressBar = binding.registerProgressBar
    }

    private fun setOnClickListener() {
        nextButton.setOnClickListener(this)
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE;
            }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE;
    }


    private fun addUserOnDB() {

    }

    private fun isValidPassword(password: String?) : Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }


    private fun createAccount(email: String, password: String) {
        var email = emailView.text.toString()
        var password = passwordView.text.toString()
        showProgressBar()
        if (isValidPassword(password)) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    hideProgressBar()
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        Toast.makeText(context, "회원가입을 환영합니다.", Toast.LENGTH_SHORT).show()
                        listener?.onNextButtonClick()
                    } else {
                        Log.d(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(context, "비밀번호 규칙에 맞지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkAllFilled() : Boolean {
        return (nameView.text.toString() != ""
                && emailView.text.toString() != ""
                && passwordView.text.toString() != "")

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = firebaseAuth?.currentUser
    }







    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        firebaseAuth = Firebase.auth
        setVariables()
        setOnClickListener()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnRegisterFragmentListener


    }

    private fun getUserInfo(): UserInfo {
        return UserInfo(
            binding.registerName.text.toString(),
            binding.registerEmail.text.toString(),
            binding.registerPassword.text.toString()
        )
    }
    override fun onClick(v :View?) {
        when (v!!.id) {
            nextButton.id -> {
                if (checkAllFilled()) {
                    userInfoViewModel.setInfo(getUserInfo())
                    listener?.onNextButtonClick() }
                else {
                    Toast.makeText(context, "안채워져있슴 ㅋㅋ", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}