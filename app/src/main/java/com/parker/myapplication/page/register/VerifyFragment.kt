import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentLoginBinding
import com.parker.myapplication.databinding.FragmentVerifyBinding
import java.security.KeyStore
import java.util.concurrent.TimeUnit

public class VerifyFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var _binding: FragmentVerifyBinding? = null
    private val binding get() = _binding!!

    private lateinit var phoneNumber : EditText
    private lateinit var verificationCode: EditText
    private lateinit var verifyPhoneButton : Button
    private lateinit var verifyRegisterButton : Button

    private lateinit var auth : FirebaseAuth
    var storedVerificationId = ""

    private fun setVariables() {
        phoneNumber = binding.verifyPhoneNumber
        verificationCode = binding.verificationCode
        verifyPhoneButton = binding.verifyPhoneButton
        verifyRegisterButton = binding.verifyRegisterButton
    }

    private fun setOnClickListener() {

        verifyPhoneButton.setOnClickListener {
            verificationCode.setBackgroundResource(R.drawable.box)
            binding.verificationCode.isEnabled = true
            verify ()
        }



        verifyRegisterButton.setOnClickListener {
            authenticate()
        }

    }

    private fun authenticate() {
        val verificationCode = verificationCode.text.toString()
        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(storedVerificationId, verificationCode)
        signInWithPhoneAuthCredential(credential)
    }


    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private fun verificationCallbacks () {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:$credential")

                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }

                // Show a message and update the UI
                // ...
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
//                resendToken = token

                // ...
            }
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                    // ...
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
    }


    private fun verify () {

        verificationCallbacks()

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this.requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }














    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVerifyBinding.inflate(inflater, container, false)
        val view = binding.root
        setVariables()
        setOnClickListener()
        auth = FirebaseAuth.getInstance()


        return view
    }
    }


