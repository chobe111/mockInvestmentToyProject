package com.parker.myapplication.page.task

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.parker.myapplication.databinding.FragmentTaskOneBinding
import com.parker.myapplication.page.register.RegisterFragment


class TaskOneFragment : Fragment() {

    private lateinit var binding: FragmentTaskOneBinding
    private lateinit var firstEditText: EditText
    private lateinit var secondEditText: EditText
    private lateinit var thirdEditText: EditText
    private lateinit var taskOneButton: Button

    private var listener: TaskOneFragmentListener? = null

    interface TaskOneFragmentListener {
        fun taskOneButtonClick()
    }


    private fun setVariables() {
        var firstEditText = binding.firstEditText
        var secondEditText = binding.secondEditText
        var thirdEditText = binding.thirdEditText
    }

    private val taskDataClassViewModel : TaskDataClassViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskOneBinding.inflate(inflater, container, false)
        val view = binding.root
        setVariables()
        val taskOneButton = binding.taskOneButton
        taskOneButton.setOnClickListener {
            taskDataClassViewModel.setInfo(getTaskDataClass())
            listener?.taskOneButtonClick()
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as TaskOneFragmentListener}

    private fun getTaskDataClass(): TaskDataClass {
        return TaskDataClass(
            binding.firstEditText.text.toString(),
            binding.secondEditText.text.toString(),
            binding.thirdEditText.text.toString()
        )
    }



}




