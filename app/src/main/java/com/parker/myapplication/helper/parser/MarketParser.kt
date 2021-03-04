package com.parker.myapplication.helper.parser

import com.parker.myapplication.data.StockInfo

object MarketParser : BaseParser<StockInfo> {
    override fun getEvents(): MutableList<StockInfo> {
        val dataList: MutableList<StockInfo> = mutableListOf()

        return dataList
    }
}