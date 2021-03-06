package com.parker.myapplication.helper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.MarketCardViewItemBinding
import com.parker.myapplication.databinding.StockCardViewItemBinding

class MarketStockListAdapter : RecyclerView.Adapter<MarketViewHolder>() {
    private var items = mutableListOf<StockInfo>()
    fun setItems(data: ArrayList<StockInfo>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MarketCardViewItemBinding.inflate(inflater, parent, false)
        return MarketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int {
        return items.size
    }
}