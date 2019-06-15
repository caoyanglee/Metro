package com.pmm.metro.demo

import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import com.pmm.metro.Station
import com.weimu.universalview.core.fragment.BaseFragment

/**
 * Author:你需要一台永动机
 * Date:2019-06-13 18:09
 * Description:
 */
@Station("/fragment/test")
class TestFragment : BaseFragment() {

    override fun beforeViewAttach(savedInstanceState: Bundle?) {
        val id = arguments?.getInt("id") ?: -1
        val name = arguments?.getString("name") ?: "无"
        Log.d("pmm", "TestFragment id=$id")
        Log.d("pmm", "TestFragment name=$name")
    }

    override fun getLayoutUI(): ViewGroup? = LinearLayoutCompat(context).apply {
        orientation = LinearLayoutCompat.VERTICAL
        addView(TextView(context).apply {
            text = "测试信号"
            textSize = 18f
        })
    }
}