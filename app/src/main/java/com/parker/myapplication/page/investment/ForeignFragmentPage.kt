package com.parker.myapplication.page.investment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.parker.myapplication.R
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.FragmentDomesticInvestmentPageBinding
import com.parker.myapplication.databinding.FragmentForeignPageBinding
import com.parker.myapplication.helper.adapter.StockListAdapter
import com.parker.myapplication.helper.parser.ForeignParser
import com.parker.myapplication.viewmodel.StockViewModel

class ForeignFragmentPage : BaseInvestmentPage() {

    private lateinit var binding: FragmentForeignPageBinding
    private val stockViewModel: StockViewModel by activityViewModels()

    private fun observeData(){
        stockViewModel.fetchData(ForeignParser).observe(viewLifecycleOwner, Observer { stockInfoList ->
            setListView(binding.stockListView)
            setListViewAdapter(stockInfoList as ArrayList<StockInfo>)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_foreign_page,
            container,
            false
        )
        val view = binding.root
        observeData()
        return view
    }

//    companion object {
//        val stockList: ArrayList<StockInfo> = arrayListOf(
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78"),
//            StockInfo("TSMC", "288,000", "2800", "-1.78")
//        )
//    }
}