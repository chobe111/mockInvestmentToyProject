package com.parker.myapplication.repository

import android.content.Context
import android.util.EventLog
import android.util.Log
import com.parker.myapplication.model.EventItem
import org.jsoup.Jsoup
import java.io.IOException

class Repo {

    companion object {
        var instance: Repo? = null
        lateinit var mContext: Context
    }

    fun getInstance(context: Context): Repo {
        mContext = context
        if(instance == null)
            instance = Repo()

        return instance!!
    }

    fun getEvents(): MutableList<EventItem>{
        val listData : MutableList<EventItem> = mutableListOf<EventItem>()
        try{
            val url = "https://m.stock.naver.com/"
            val doc = Jsoup.connect(url).get()
            val events = doc.getElementsByClass("stock_dn")
            val eventsSize = events.size
            Log.d("junho", eventsSize.toString())
            for (i in 0 until eventsSize) {
                val title = events.select("span.no_wrp")
                    .eq(i)
                    .text()
                val price = events.select("span.stock_item")
                    .eq(i)
                    .text()
                val date = events.select("span.value-title")
                    .eq(i)
                    .text()
                val desc = events.select("")
                    .eq(i)
                    .text()
                val eventUrl = events.select("")
                    .select("a")
                    .eq(i)
                    .attr("href")
                listData.add(EventItem(i,title,price,date,desc,eventUrl))
            }
        } catch (e: IOException){
            e.printStackTrace()
        }
        return listData
    }

}