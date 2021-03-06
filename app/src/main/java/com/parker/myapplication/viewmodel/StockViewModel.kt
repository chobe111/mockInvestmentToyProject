package com.parker.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.helper.parser.BaseParser
import com.parker.myapplication.helper.parser.DomesticParser
import com.parker.myapplication.helper.parser.ForeignParser
import com.parker.myapplication.helper.parser.MarketParser
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {
    var marketitems: MutableLiveData<MutableList<StockInfo>> = MutableLiveData()
    var foreignItems: MutableLiveData<MutableList<StockInfo>> = MutableLiveData()
    var domesticItems: MutableLiveData<MutableList<StockInfo>> = MutableLiveData()
    fun fetchData(parser: BaseParser<StockInfo>): MutableLiveData<MutableList<StockInfo>> {
//      Async
        viewModelScope.launch(IO) {
            when (parser) {
                DomesticParser -> domesticItems.postValue(parser.getEvents())
                ForeignParser -> foreignItems.postValue(parser.getEvents())
                MarketParser -> marketitems.postValue(parser.getEvents())
            }
        }
        return when (parser) {
            DomesticParser -> domesticItems
            ForeignParser -> foreignItems
            else -> marketitems
        }
    }
}