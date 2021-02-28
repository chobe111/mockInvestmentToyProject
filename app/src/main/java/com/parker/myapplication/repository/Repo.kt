package com.parker.myapplication.repository

import com.parker.myapplication.data.EventItem
import org.jsoup.Jsoup
import java.io.IOException

object Repo {
    fun getEvents(): MutableList<EventItem>{
        val dataList : MutableList<EventItem> = mutableListOf<EventItem>()
        try{
            val url = "https://finance.naver.com/sise/sise_market_sum.nhn?&page=1"
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
                dataList.add(EventItem(tdLen ,name, price, priceGap, gap))
            }
        } catch (e: IOException){
            e.printStackTrace()
        }
        return dataList
    }

}