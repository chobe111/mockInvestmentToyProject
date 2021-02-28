package com.parker.myapplication.page.investment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.parker.myapplication.R
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.FragmentForeignPageBinding

// TODO: Rename parameter arguments, choose names that match

class ForeignFragmentPage : BaseInvestmentPage() {

    private lateinit var binding: FragmentForeignPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForeignPageBinding.inflate(inflater, container, false)
        val view = binding.root
        setListView(binding.stockListView)
        setListViewAdapter(stockList)
        return view
    }

    companion object {
        val stockList: ArrayList<StockInfo> = arrayListOf(
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78"),
            StockInfo("TSMC", "288,000", "2800", "-1.78")
        )
    }
}