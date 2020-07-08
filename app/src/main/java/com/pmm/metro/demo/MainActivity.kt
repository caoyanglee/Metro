package com.pmm.metro.demo

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.pmm.metro.Metro
import com.pmm.metro.annotatoin.Station
import com.pmm.metro.demo.transfer.UserCheckTransfer
import kotlinx.android.synthetic.main.activity_main.*

@Station("/main")
class MainActivity : AppCompatActivity() {

    private var testService: TestService.TestBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "http://www.baidu.com"

        mTvJump.click {
            Metro.with(this)
                .path("/a?name=你需要一台永动机&age=27&url=$url")
                .addTransfer(UserCheckTransfer())
                .fail {
                    Log.e("metro", it.toString())
                }
                .go()
        }

        mBtnService.click {
            startServices()
        }

        mBtnGetName.click {
            toast("${testService?.getName()}")
        }

        mBtnFragment.click {
            Metro.with(this)
                .path("/fragment/test")
                .put("id", 3)
                .put("name", "你需要一台永动机")
                .fragmentLauncher()
                .go()
        }

        mBtnFail.click {
            Metro.with(this)
                .path("/webview")
                .fail { toast(it.message.toString()) }
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
            .serviceLauncher()
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
