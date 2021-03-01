package com.parker.myapplication.page.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.parker.myapplication.databinding.FragmentTaskTwoBinding


class TaskTwoFragment : Fragment() {

    private lateinit var binding: FragmentTaskTwoBinding
    private lateinit var firstTextView : TextView
    private lateinit var secondTextView: TextView
    private lateinit var thirdTextView: TextView


    private val taskDataClassViewModel : TaskDataClassViewModel by activityViewModels()

    private fun setVariables() {
        firstTextView = binding.firstTextView
        secondTextView = binding.secondTextView
        thirdTextView = binding.thirdTextView
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskTwoBinding.inflate(inflater, container, false)

        val view = binding.root
        setVariables()
        taskDataClassViewModel.getUserInfo().observe(viewLifecycleOwner, androidx.lifecycle.Observer { taskDataClass ->
            firstTextView!!.text = taskDataClass.first
            secondTextView!!.text = taskDataClass.second
            thirdTextView!!.text = taskDataClass.third
        })
        return view

    }


}