package com.parker.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parker.myapplication.model.UserInfo

class UserInfoViewModel : ViewModel() {
    val userInfo = MutableLiveData<UserInfo>()
    fun setInfo(userInfo: UserInfo) {
        this.userInfo.value = userInfo
    }

    fun getUserInfo(): LiveData<UserInfo> {
        return userInfo
    }
}
