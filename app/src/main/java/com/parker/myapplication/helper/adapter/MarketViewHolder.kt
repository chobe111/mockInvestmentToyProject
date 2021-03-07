package com.parker.myapplication.helper.adapter

import android.graphics.Color
import androidx.databinding.library.baseAdapters.BR
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.MarketCardViewItemBinding
import androidx.recyclerview.widget.RecyclerView
import com.parker.myapplication.R
import com.parker.myapplication.helper.Discriminator.isPriceUp
class MarketViewHolder(val binding: MarketCardViewItemBinding):
        RecyclerView.ViewHolder(binding.root){
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