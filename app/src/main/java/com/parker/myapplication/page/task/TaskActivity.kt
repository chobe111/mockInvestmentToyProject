package com.parker.myapplication.page.task

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.parker.myapplication.R
import com.parker.myapplication.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity(), TaskOneFragment.TaskOneFragmentListener {

    private var _binding: ActivityTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskButton: Button

    private val taskOneFragment by lazy { TaskOneFragment() }
    private val taskTwoFragment by lazy { TaskTwoFragment() }



    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTaskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        taskButton = _binding!!.taskButton
        taskButton.setOnClickListener{ changeFragment(taskOneFragment) }
    }

    override fun taskOneButtonClick() {
        changeFragment(taskTwoFragment)
    }
}