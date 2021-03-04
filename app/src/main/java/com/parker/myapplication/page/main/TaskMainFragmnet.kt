package com.parker.myapplication.page.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.parker.myapplication.R
import com.parker.myapplication.databinding.ActivityMainBinding
import com.parker.myapplication.databinding.ActivityTask1Binding
import com.parker.myapplication.databinding.FragmentTaskMainBinding
import com.parker.myapplication.databinding.FragmentTaskPageOneBinding

class TaskMainFragment : Fragment() {

    private lateinit var binding: FragmentTaskMainBinding
    private lateinit var listener: OnButtonClickEvent

    interface OnButtonClickEvent {
        fun onButtonClick()
        fun onTaskOneDone()
    }

    private fun setClickEvent() {
        val button = binding.startButton
        button.setOnClickListener {
            listener.onButtonClick()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskMainBinding.inflate(inflater, container, false)
        val view = binding.root
        val button = binding.startButton
        setClickEvent()
        return view
    }

    private fun buttonClick() {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnButtonClickEvent
    }
}