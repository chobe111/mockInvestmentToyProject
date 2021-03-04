package com.parker.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.helper.parser.BaseParser
import com.parker.myapplication.helper.parser.DomesticParser
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class StockViewModel: ViewModel() {
    var items: MutableLiveData<MutableList<StockInfo>> = MutableLiveData()

    fun fetchData(parser: BaseParser): MutableLiveData<MutableList<StockInfo>>{
        viewModelScope.launch(IO) {
            items.postValue(parser.getEvents())
        }
        return items
    }

}