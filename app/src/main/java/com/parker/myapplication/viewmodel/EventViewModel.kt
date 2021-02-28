package com.parker.myapplication.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parker.myapplication.model.EventItem
import com.parker.myapplication.repository.Repo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class EventViewModel: ViewModel() {

    var items: MutableLiveData<MutableList<EventItem>> = MutableLiveData()

    fun init (context: Context){
        if (items.value != null)
            return
    }

    private val repo = Repo()
    fun fetchData(): MutableLiveData<MutableList<EventItem>>{
        viewModelScope.launch(IO) {
            items.postValue(repo.getEvents())
        }
        return items
    }

}