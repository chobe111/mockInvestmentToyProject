package com.parker.myapplication.helper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.parker.myapplication.R
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.StockCardViewItemBinding

class StockListAdapter(val context: Context, val stockInfo: ArrayList<StockInfo>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//      TODO: Change ListView to RecyclerView
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.stock_card_view_item, parent, false)

        val stockInfo: StockInfo = getItem(position)
        initView(view, stockInfo)
        return view
    }

    private fun isPriceUp(): Boolean {
        return false
    }

    private fun initView(view: View, stockInfo: StockInfo) {
        val stockItem: TextView = view.findViewById(R.id.stock_item)
        val gapRate: TextView = view.findViewById(R.id.gap_rate)
        val price: TextView = view.findViewById(R.id.current_price)
        val gapPrice: TextView = view.findViewById(R.id.gap_price)

        stockItem.text = stockInfo.name
        gapRate.text = stockInfo.gapRate
        price.text = stockInfo.currentPrice
        gapPrice.text = stockInfo.gapPrice
    }

    override fun getCount(): Int {
        return stockInfo.size
    }

    override fun getItem(position: Int): StockInfo {
        return stockInfo[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}