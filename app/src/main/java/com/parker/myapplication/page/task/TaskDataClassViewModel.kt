package com.parker.myapplication.page.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskDataClassViewModel : ViewModel() {
    val taskDataClass = MutableLiveData<TaskDataClass>()
    fun setInfo(taskDataClass: TaskDataClass) {
        this.taskDataClass.value = taskDataClass
    }

    fun getUserInfo(): LiveData<TaskDataClass> {
        return this.taskDataClass
    }

}