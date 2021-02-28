package com.parker.myapplication.page.investment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import com.parker.myapplication.R
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.FragmentDomesticInvestmentPageBinding
import com.parker.myapplication.helper.adapter.StockListAdapter

class DomesticInvestmentPage : BaseInvestmentPage() {

    private lateinit var binding: FragmentDomesticInvestmentPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_domestic_investment_page,
            container,
            false
        )
        val view = binding.root
        setListView(binding.stockListView)
        setListViewAdapter(stockList)
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