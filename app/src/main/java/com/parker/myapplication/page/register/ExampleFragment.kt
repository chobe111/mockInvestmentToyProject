package com.parker.myapplication.page.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentExampleBinding
import com.parker.myapplication.viewmodel.UserInfoViewModel

class ExampleFragment : Fragment() {
    // 2/23 새로 추가된 코드.

    private lateinit var binding: FragmentExampleBinding
    private val viewModel: UserInfoViewModel by activityViewModels()
    private var emailView: TextView? = null
    private var nameView: TextView? = null
    private var passwordView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExampleBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        emailView = binding.email
        nameView = binding.nickname
        passwordView = binding.password

        viewModel.getUserInfo().observe(viewLifecycleOwner, Observer { userinfo ->
            emailView!!.text = userinfo.email
            nameView!!.text = userinfo.nickname
            // UserInfo.kt에 name이라고 되어 있었던 것 때문에 오류 떴었음.
            // ctrl + 클릭하면 해당 키워드가 어디로 이어지는지 알 수 있음. 이걸로 코드 분석하기!
            passwordView!!.text = userinfo.password
        })
        return view

        // Inflate the layout for this fragment
    }

}
