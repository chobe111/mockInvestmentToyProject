package com.parker.myapplication.page.market

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.parker.myapplication.R
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.FragmentMarketBinding
import com.parker.myapplication.helper.TAG
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

        GlobalScope.launch {
            val datalist: MutableList<StockInfo> = arrayListOf()

            val doc: Document =
                Jsoup.connect("https://finance.naver.com/sise/sise_market_sum.nhn?&page=1").get()
            val wholeStockData = doc.select("table[class=type_2]").select("tbody").select("tr")
            Log.d(TAG, "onCreateView: asdfadfadf")
            for (data in wholeStockData) {
                val tdLen = data.select("td").size
                if (tdLen <= 5) continue
                val tdList = data.select("td")
                var name = ""
                var price = ""
                var priceGap = ""
                var gap = ""
                tdList.forEachIndexed { index, element ->
                    when(index){
                        1 -> {
                            name = element.select("a").text()
                        }
                        2 -> {
                            price = element.text()
                        }
                        3 -> {
                            priceGap = element.text()
                        }
                        4 -> {
                            gap = element.text()
                        }
                    }
                }
                datalist.add(StockInfo(name, price, priceGap, gap))
            }
        }



        return view
    }
}