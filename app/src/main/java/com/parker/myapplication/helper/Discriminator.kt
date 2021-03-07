package com.parker.myapplication.helper

object Discriminator {
    fun isPriceUp(gapRate: String): Boolean {
        return gapRate[0] == '+'
    }

}