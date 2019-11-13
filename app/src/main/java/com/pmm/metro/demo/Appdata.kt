package com.pmm.metro.demo

import android.util.Log
import com.pmm.metro.*
import com.pmm.metro.transfer.Transfer
import com.pmm.ui.OriginAppData

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 14:40
 * Description:
 */
class Appdata : OriginAppData() {

    override fun isDebug(): Boolean = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()
        MetroConfig()
    }

    private fun MetroConfig() {
        Metro.init(this)

        //中转站
        MetroMap.addTransfer(object : Transfer {

            override fun transfer(chain: Transfer.Chain): Ticket {
                val ticket = chain.ticket()
                Log.d("metro", "目的站=${ticket.path}")
                return chain.proceed(ticket)
            }
        })
        //代码方式 增加站点
        MetroMap.addStation("/index", MainActivity::class.java)
    }
}