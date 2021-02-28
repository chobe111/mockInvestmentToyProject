package com.parker.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.helper.parser.Parser
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class StockViewModel: ViewModel() {
    var items: MutableLiveData<MutableList<StockInfo>> = MutableLiveData()


    fun fetchData(): MutableLiveData<MutableList<StockInfo>>{
        viewModelScope.launch(IO) {
            items.postValue(Parser.getEvents())
        }
        return items
    }

}