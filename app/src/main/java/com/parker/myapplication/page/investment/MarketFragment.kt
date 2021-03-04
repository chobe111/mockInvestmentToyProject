package com.parker.myapplication.page.investment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.parker.myapplication.R
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.FragmentMarketBinding
import com.parker.myapplication.helper.adapter.BaseStockListAdapter
import com.parker.myapplication.helper.parser.ForeignParser
import com.parker.myapplication.viewmodel.StockViewModel


class MarketFragment : Fragment() {

    private lateinit var binding: FragmentMarketBinding
    private lateinit var gridView: RecyclerView
    private val stockViewModel: StockViewModel by activityViewModels()


    private fun setGridView() {
        gridView = binding.marketGridView
        gridView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
    }

    private fun observeData(){
        stockViewModel.fetchData(ForeignParser).observe(viewLifecycleOwner, Observer { stockInfoList ->
            setGridView()
            setGridAdapter(stockInfoList as ArrayList<StockInfo>)
        })
    }

    private fun setGridAdapter(stockList: ArrayList<StockInfo>) {
        gridView.adapter = BaseStockListAdapter().apply { setItems(stockList) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketBinding.inflate(inflater, container, false)
        val view = binding.root
        observeData()
        // Inflate the layout for this fragment
        return view

    }

}