package com.pmm.metro.demo

import com.orhanobut.logger.Logger
import com.pmm.metro.Metro
import com.pmm.metro.MetroMap
import com.pmm.metro.Ticket
import com.pmm.metro.Transfer
import com.weimu.universalview.OriginAppData

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 14:40
 * Description:
 */
class Appdata : OriginAppData() {

    override fun isDebug(): Boolean = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()

        Metro.init(this)

        //中转站
        MetroMap.addTransfer(object : Transfer {
            override fun transfer(ticket: Ticket): Ticket {
                Logger.d("目的站=${ticket.path}")
                return ticket
            }
        })
        //代码方式 增加站点
        MetroMap.addStation("/index", MainActivity::class.java)

    }
}