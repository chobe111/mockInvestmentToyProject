package com.parker.myapplication.page.investment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import com.parker.myapplication.R
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.FragmentInvestmentBinding
import com.parker.myapplication.helper.adapter.StockListAdapter

// TODO: Rename parameter arguments, choose names that match

class InvestmentFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentInvestmentBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_investment, container, false)
        val view = binding.root
        return view
    }

    companion object {
        val stockList: ArrayList<StockInfo> = arrayListOf(
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78"),
            StockInfo("삼성전자", "288,000", "2800", "-1.78")
        )
    }
}