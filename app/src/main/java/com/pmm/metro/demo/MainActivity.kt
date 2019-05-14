package com.pmm.metro.demo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pmm.metro.Metro
import com.pmm.metro.Station
import com.weimu.universalview.ktx.setOnClickListenerPro
import com.weimu.universalview.ktx.toast
import kotlinx.android.synthetic.main.activity_main.*

@Station("/main")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTvJump.setOnClickListenerPro {
            Metro.with(this)
                .path("/a")
                .transfer(LoginTransferStation())
                .attribute("name", "你需要一台永动机")
                .go()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            toast("返回成功")
        }
    }
}
