package com.parker.myapplication.helper.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.parker.myapplication.page.investment.DomesticInvestmentPage
import com.parker.myapplication.page.investment.ForeignFragmentPage

class FragmentSlideAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
    private val investmentPageList = arrayListOf(
        DomesticInvestmentPage(),
        ForeignFragmentPage()
    )

    override fun getItemCount(): Int {
        return investmentPageList.size
    }

    override fun createFragment(position: Int): Fragment = investmentPageList[position]
}