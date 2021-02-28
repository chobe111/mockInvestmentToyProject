package com.parker.myapplication.helper.adapter

import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.parker.myapplication.R
import com.parker.myapplication.data.EventItem
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.StockCardViewItemBinding

class BaseStockListAdapter : RecyclerView.Adapter<BaseStockListAdapter.ViewHolder>() {
    private var items = mutableListOf<StockInfo>()
    fun setItems(data: ArrayList<StockInfo>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseStockListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StockCardViewItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    private fun isPriceUp(gapRate: String): Boolean {
        return gapRate[0] == '+'
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: StockCardViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StockInfo) = with(itemView) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
            if (isPriceUp(item.gapRate)){
                binding.gapPrice.setTextColor(Color.parseColor("#DA4841"))
                binding.gapRate.setBackgroundResource(R.drawable.up_gap_rate)
                binding.gapImage.setImageResource(R.drawable.ic_up_arrow)
            }
        }
    }
}