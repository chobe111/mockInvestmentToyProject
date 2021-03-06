package com.parker.myapplication.helper.parser

import com.parker.myapplication.data.StockInfo
import org.jsoup.Jsoup
import java.io.IOException

object MarketParser : BaseParser<StockInfo> {
    override fun getEvents(): MutableList<StockInfo> {
        val dataList: MutableList<StockInfo> = mutableListOf()
        try {
            val url = "https://kr.investing.com/equities/most-active-stocks?country=usa"
            val doc = Jsoup.connect(url).get()
            val wholeStockData = doc.select("table[class=genTbl closedTbl elpTbl elp25 crossRatesTbl]").select("tbody").select("tr")
            for (data in wholeStockData) {
                val tdLen = data.select("td").size
                if (tdLen <= 5) continue
                val tdList = data.select("td")
                var name = ""
                var price = ""
                var priceGap = ""
                var gap = ""
                tdList.forEachIndexed { index, element ->
                    when (index) {
                        1 -> {
                            name = element.select("a").text()
                        }
                        2 -> {
                            price = element.text()
                        }
                        5 -> {
                            priceGap = element.text()
                        }
                        6 -> {
                            gap = element.text()
                        }
                    }
                }
                dataList.add(StockInfo(name, price, priceGap, gap))
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return dataList
    }
}