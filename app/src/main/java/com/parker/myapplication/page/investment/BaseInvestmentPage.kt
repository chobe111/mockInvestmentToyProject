package com.parker.myapplication.page.investment

import android.widget.ListView
import androidx.fragment.app.Fragment
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.helper.adapter.StockListAdapter

open class BaseInvestmentPage : Fragment() {
    private lateinit var listView: ListView

    fun setListView(listView: ListView) {
        this.listView = listView
    }

    fun setListViewAdapter(stockList: ArrayList<StockInfo>) {
        listView.adapter = StockListAdapter(requireContext(), stockList)
    }

}