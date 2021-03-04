package com.parker.myapplication.page.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.parker.myapplication.databinding.FragmentTaskPageOneBinding
import com.parker.myapplication.model.HumanInfo
import com.parker.myapplication.viewmodel.HumanInfoViewModel
import java.util.List.of

class TaskPageOneFragment : Fragment(), View.OnClickListener {

        private lateinit var binding: FragmentTaskPageOneBinding
        private lateinit var signUpButton: Button
        private lateinit var nameView: EditText
        private lateinit var genderView: EditText
        private lateinit var ageView: EditText

        private var listener: OnTaskOneDoneListener? = null

        private val humanViewModel: HumanInfoViewModel by activityViewModels()

        interface OnTaskOneDoneListener {
            fun onTaskOneDone()
        }

        private fun setVariables() {
            signUpButton = binding.button4
            nameView = binding.urName
            genderView = binding.gender
            ageView = binding.age
        }

        private fun setOnClickListener() {
            signUpButton.setOnClickListener(this)
        }

        private fun createInstance(name: String, gender: String, age: String):
            HumanInfo{
                return HumanInfo(name, gender, age)
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            binding = FragmentTaskPageOneBinding.inflate(inflater, container, false)
            val view = binding.root
            setVariables()
            setOnClickListener()
            return view
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            listener = context as OnTaskOneDoneListener
        }

        private fun checkAllAreaFilled(): Boolean {
            return !(nameView.text.equals("") || genderView.text.equals("") || nameView.text.equals(""))
        }

        private fun getHuman(): HumanInfo {
            return HumanInfo(
                binding.urName.text.toString(),
                binding.gender.text.toString(),
                binding.age.text.toString()
            )
        }

        override fun onClick(v: View?) {
            when (v!!.id) {
                signUpButton.id -> {
                    if (checkAllAreaFilled()) {
                        humanViewModel.setInfo(getHuman())
                        listener!!.onTaskOneDone()
                    } else {
                    createInstance(nameView.text.toString(), genderView.text.toString(), ageView.text.toString())
                    }
                }
            }
        }
    }