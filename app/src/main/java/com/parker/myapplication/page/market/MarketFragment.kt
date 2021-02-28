package com.parker.myapplication.page.market

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.parker.myapplication.R
import com.parker.myapplication.databinding.FragmentMarketBinding
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

// TODO: Rename parameter arguments, choose names that match

class MarketFragment : Fragment() {

    private lateinit var binding: FragmentMarketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_market, container, false)
        val view = binding.root

        val doc: Document = Jsoup.connect("https://m.stock.naver.com/sise/siseList.nhn?menu=market_sum&sosok=0").get()
        val wholeStockData = doc.select("tbody[class=_list_wrap]")

        return view
    }
}