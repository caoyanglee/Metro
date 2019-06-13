package com.pmm.metro.demo

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.pmm.metro.Metro
import com.pmm.metro.Station
import com.weimu.universalview.ktx.setOnClickListenerPro
import com.weimu.universalview.ktx.toast
import kotlinx.android.synthetic.main.activity_main.*

@Station("/main")
class MainActivity : AppCompatActivity() {

    private var testService: TestService.TestBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTvJump.setOnClickListenerPro {
            Metro.with(this)
                .path("/a?name=你需要一台永动机&age=27")
                //.addTransferStation(UserCheckTransferStation())
                .go()
        }

        mBtnService.setOnClickListenerPro {
            startServices()
        }

        mBtnGetName.setOnClickListenerPro {
            toast("${testService?.getName()}")
        }

        mBtnFragment.setOnClickListenerPro {
            Metro.with(this).path("/fragment/test")
                .direct2Fragment()
                .go()
        }
    }

    private fun startServices() {
        val conn = object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                testService = service as TestService.TestBinder
            }

        }

        Metro.with(this)
            .path("/service/test")
            .direct2Service()
            .go()
//        startService(Intent(this, TestService::class.java))
//        bindService(Intent(this, TestService::class.java), conn, Context.BIND_AUTO_CREATE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            toast("返回成功")
        }
    }
}
