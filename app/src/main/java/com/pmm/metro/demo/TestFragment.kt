package com.pmm.metro.demo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.pmm.metro.annotatoin.Station

/**
 * Author:你需要一台永动机
 * Date:2019-06-13 18:09
 * Description:
 */
@Station("/fragment/test")
class TestFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getInt("id") ?: -1
        val name = arguments?.getString("name") ?: "无"
        Log.d("pmm", "TestFragment id=$id")
        Log.d("pmm", "TestFragment name=$name")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getLayoutUI()
    }

    private fun getLayoutUI(): ViewGroup? = LinearLayoutCompat(context).apply {
        orientation = LinearLayoutCompat.VERTICAL
        addView(TextView(context).apply {
            text = "测试信号"
            textSize = 18f
        })
    }
}