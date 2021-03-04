package com.parker.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.parker.myapplication.databinding.ActivityTask1Binding
import com.parker.myapplication.page.main.TaskMainFragment
import com.parker.myapplication.page.main.TaskPageOneFragment
import com.parker.myapplication.page.main.TaskPageTwoFragment

class Task1Activity : AppCompatActivity(), TaskMainFragment.OnButtonClickEvent, TaskPageOneFragment.OnTaskOneDoneListener {

    private var _binding : ActivityTask1Binding? = null
    private val binding get() = _binding!!

    private val taskMainFragment : TaskMainFragment by lazy { TaskMainFragment() }
    private val taskPageOneFragment : TaskPageOneFragment by lazy { TaskPageOneFragment() }
    private val taskPageTwoFragment : TaskPageTwoFragment by lazy { TaskPageTwoFragment() }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTask1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        changeFragment(taskMainFragment)
    }

    override fun onButtonClick() {
        changeFragment(taskPageOneFragment)
    }

    override fun onTaskOneDone() {
        changeFragment(taskPageTwoFragment)
    }
}
