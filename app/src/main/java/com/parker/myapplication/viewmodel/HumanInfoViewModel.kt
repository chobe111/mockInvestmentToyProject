package com.parker.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parker.myapplication.model.HumanInfo

class HumanInfoViewModel : ViewModel() {
    val human = MutableLiveData<HumanInfo>()
    fun setInfo(human: HumanInfo) {
        this.human.value = human
    }

    fun getHuman(): LiveData<HumanInfo> {
        return this.human
    }
}