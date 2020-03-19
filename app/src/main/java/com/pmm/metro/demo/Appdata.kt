package com.pmm.metro.demo

import android.util.Log
import com.pmm.metro.*
import com.pmm.metro.transfer.Transfer
import com.pmm.ui.OriginAppData
import kotlin.system.measureTimeMillis

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 14:40
 * Description:
 */
class AppData : OriginAppData() {

    override fun isDebug(): Boolean = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()
        MetroConfig()
    }

    private fun MetroConfig() {
        val time1 = measureTimeMillis{ Metro.init(this, true) }
        val time2 =measureTimeMillis{ Metro.loadConfigClass("com.pmm.metro.route.MetroRoute_app") }

        Log.d("timeTest","time1=$time1")
        Log.d("timeTest","time2=$time2")

        //中转站
        MetroMap.addTransfer(object : Transfer {

            override fun transfer(chain: Transfer.Chain): Ticket {
                val ticket = chain.ticket()
                Log.d("metro", "全局中转站：目的站=${ticket.path}")
                return chain.proceed(ticket)
            }
        })
        //代码方式 增加站点
        MetroMap.addStation("/index", MainActivity::class.java)
    }
}