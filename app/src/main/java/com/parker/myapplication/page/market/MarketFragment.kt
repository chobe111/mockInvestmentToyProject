package com.parker.myapplication.page.market

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.parker.myapplication.R
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.FragmentMarketBinding
import com.parker.myapplication.helper.TAG
import com.parker.myapplication.helper.adapter.MarketStockListAdapter
import com.parker.myapplication.helper.parser.MarketParser
import com.parker.myapplication.viewmodel.StockViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

// TODO: Rename parameter arguments, choose names that match

class MarketFragment : Fragment() {

    private lateinit var binding: FragmentMarketBinding
    private lateinit var gridView: RecyclerView
    private val stockViewModel: StockViewModel by activityViewModels()


    private fun setGridView() {
        gridView = binding.marketGridView
        gridView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
    }

    private fun observeData(){
        stockViewModel.fetchData(MarketParser).observe(viewLifecycleOwner, Observer { stockInfoList ->
            setGridView()
            setGridAdapter(stockInfoList as ArrayList<StockInfo>)
        })
    }

    private fun setGridAdapter(stockList: ArrayList<StockInfo>) {
        gridView.adapter = MarketStockListAdapter().apply { setItems(stockList) }
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