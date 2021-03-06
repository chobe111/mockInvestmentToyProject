package com.parker.myapplication.helper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parker.myapplication.databinding.EventItemBinding
import com.parker.myapplication.data.EventItem

class EventAdapter(private val context: Context): RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private var items = mutableListOf<EventItem>()

    fun setListData(data: MutableList<EventItem>){
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: EventItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: EventItem) = with(itemView){
//            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
//            Picasso.get().load(item.image).into(binding.imageView)
        }
    }

}