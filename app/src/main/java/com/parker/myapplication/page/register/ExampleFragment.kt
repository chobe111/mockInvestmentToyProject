package com.parker.myapplication.page.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentExampleBinding
import com.parker.myapplication.page.viewmodel.UserInfoViewModel
import java.util.Observer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExampleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExampleFragment : Fragment() {

    private var emailView : TextView? = null
    private var passwordView : TextView? = null
    private var nicknameView : TextView? = null

    private lateinit var binding : FragmentExampleBinding
    private val viewModels : UserInfoViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExampleBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        emailView = binding.email
        passwordView = binding.password
        nicknameView = binding.nickname

        viewModels.getUserInfo().observe(viewLifecycleOwner, androidx.lifecycle.Observer { userinfo ->
            emailView!!.text = userinfo.email
            nicknameView!!.text = userinfo.nickname
            passwordView!!.text = userinfo.password


        })

        // Inflate the layout for this fragment
        return view
    }

}