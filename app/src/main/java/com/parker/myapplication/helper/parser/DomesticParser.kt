package com.parker.myapplication.helper.parser

import com.parker.myapplication.data.StockInfo
import org.jsoup.Jsoup
import java.io.IOException

object DomesticParser: BaseParser {
    override fun getEvents(): MutableList<StockInfo> {
        val dataList: MutableList<StockInfo> = mutableListOf()
        try {
            for (page in 1..3) {
                val url = "https://finance.naver.com/sise/sise_market_sum.nhn?&page=${page}"
                val doc = Jsoup.connect(url).get()
                val wholeStockData = doc.select("table[class=type_2]").select("tbody").select("tr")
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
                            3 -> {
                                priceGap = element.text()
                            }
                            4 -> {
                                gap = element.text()
                            }
                        }
                    }
                    dataList.add(StockInfo(name, price, priceGap, gap))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return dataList
    }

}