package com.parker.myapplication.helper.parser

interface BaseParser<T> {
    fun getEvents(): MutableList<T>
}