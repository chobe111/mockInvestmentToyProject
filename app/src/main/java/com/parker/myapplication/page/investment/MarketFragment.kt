package com.parker.myapplication.page.investment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentMarketBinding

// TODO: Rename parameter arguments, choose names that match

class MarketFragment : Fragment() {

    private lateinit var binding: FragmentMarketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_market, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

}