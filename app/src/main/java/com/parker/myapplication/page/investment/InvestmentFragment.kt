package com.parker.myapplication.page.investment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.parker.myapplication.R
import com.parker.myapplication.data.StockInfo
import com.parker.myapplication.databinding.FragmentInvestmentBinding
import com.parker.myapplication.helper.adapter.FragmentSlideAdapter
import com.parker.myapplication.helper.adapter.StockListAdapter

// TODO: Rename parameter arguments, choose names that match

class InvestmentFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentInvestmentBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    private fun setTabLayout() {
        tabLayout = binding.topTabLayout
    }

    private fun setViewPager() {
        viewPager = binding.investFragmentContainer
        val pagerAdapter = FragmentSlideAdapter(this)
        viewPager.adapter = pagerAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInvestmentBinding.inflate(inflater, container, false)
        val view = binding.root
        setViewPager()
        setTabLayout()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position == 0) {
                tab.setText("국내")
            } else {
                tab.setText("해외")
            }
        }.attach()
    }
}