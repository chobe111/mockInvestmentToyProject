package com.parker.myapplication.page.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.parker.myapplication.MainActivity
import com.parker.myapplication.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignInCheckFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInCheckFragment : Fragment() {
    // TODO: Rename and change types of parameters

    interface SignInCheckListener {
        fun signedIn()
        fun notSignedIn()
    }

    private var listener: SignInCheckListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var user = Firebase.auth.currentUser
        if (user != null) listener!!.signedIn() else { listener!!.notSignedIn() }
        return inflater.inflate(R.layout.fragment_sign_in_check, container, false)
    }





}