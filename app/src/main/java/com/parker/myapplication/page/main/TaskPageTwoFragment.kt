package com.parker.myapplication.page.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.parker.myapplication.databinding.FragmentTaskPageTwoBinding
import com.parker.myapplication.viewmodel.HumanInfoViewModel

class TaskPageTwoFragment : Fragment() {
    private lateinit var binding: FragmentTaskPageTwoBinding

    private lateinit var textView7: TextView
    private lateinit var textView8: TextView
    private lateinit var textView9: TextView

    private val humanInfoViewModel : HumanInfoViewModel by activityViewModels()

    private fun setVariables() {
        textView7 = binding.textView7
        textView8 = binding.textView8
        textView9 = binding.textView9
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskPageTwoBinding.inflate(inflater, container, false)

        val view = binding.root
        setVariables()
        humanInfoViewModel.getHuman().observe(viewLifecycleOwner, androidx.lifecycle.Observer { human ->
          textView7!!.text = human.name
          textView8!!.text = human.gender
          textView9!!.text = human.age
        })
        return view
    }
}