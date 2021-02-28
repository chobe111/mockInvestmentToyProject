package com.parker.myapplication.page.investment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.parker.myapplication.R
import com.parker.myapplication.adapters.EventAdapter
import com.parker.myapplication.databinding.FragmentInvestmentBinding
import com.parker.myapplication.viewmodel.EventViewModel


class InvestmentFragment : Fragment() {

    private lateinit var binding: FragmentInvestmentBinding
    private  val viewModel by lazy { ViewModelProvider(this).get(EventViewModel::class.java) }
    private  lateinit var adapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInvestmentBinding.inflate(inflater, container, false)

        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapter = EventAdapter(requireActivity())
        binding.recyclerView.adapter = adapter

        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            binding.progressBar.visibility = View.GONE
        })

        return binding.root

    }
}