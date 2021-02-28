package com.parker.myapplication.page.investment

import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.helper.adapter.BaseStockListAdapter
import com.parker.myapplication.helper.adapter.StockListAdapter

open class BaseInvestmentPage : Fragment() {
    private lateinit var listView: RecyclerView

    fun setListView(listView: RecyclerView) {
        this.listView = listView
    }

    fun setListViewAdapter(stockList: ArrayList<StockInfo>) {
        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = BaseStockListAdapter().apply { setItems(stockList) }
    }

}