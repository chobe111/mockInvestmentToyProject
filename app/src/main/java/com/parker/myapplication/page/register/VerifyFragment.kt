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
        }



        verifyRegisterButton.setOnClickListener {

        }



    }






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVerifyBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        setVariables()
        setOnClickListener()


        return view
    }
    }


