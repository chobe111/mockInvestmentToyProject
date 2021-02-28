package com.parker.myapplication.helper.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
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

    private fun isPriceUp(gapRate: String): Boolean {
        return gapRate[0] == '+'
    }


    @SuppressLint("ResourceAsColor")
    private fun initView(view: View, stockInfo: StockInfo) {
        val stockItem: TextView = view.findViewById(R.id.stock_item)
        val gapRate: TextView = view.findViewById(R.id.gap_rate)
        val price: TextView = view.findViewById(R.id.current_price)
        val gapPrice: TextView = view.findViewById(R.id.gap_price)
        val gapImage: ImageView = view.findViewById(R.id.gap_image)

        stockItem.text = stockInfo.name
        gapRate.text = stockInfo.gapRate
        price.text = stockInfo.currentPrice
        gapPrice.text = stockInfo.gapPrice

        if (isPriceUp(stockInfo.gapRate)) {
            gapPrice.setTextColor(Color.parseColor("#DA4841"))
            gapRate.setBackgroundResource(R.drawable.up_gap_rate)
            gapImage.setImageResource(R.drawable.ic_up_arrow)
        }
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