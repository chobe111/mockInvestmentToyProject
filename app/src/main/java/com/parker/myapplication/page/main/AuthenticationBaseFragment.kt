package com.parker.myapplication.page.main

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

open class AuthenticationBaseFragment : Fragment() {
    private var progressBar: ProgressBar? = null

    fun setProgressBar(bar: ProgressBar) {
        progressBar = bar
    }

    fun showProgressBar() {
        progressBar!!.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar!!.visibility = View.INVISIBLE
    }
}