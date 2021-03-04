package com.parker.myapplication.helper.parser

import com.parker.myapplication.data.StockInfo

interface BaseParser {
    fun getEvents(): MutableList<StockInfo>
}