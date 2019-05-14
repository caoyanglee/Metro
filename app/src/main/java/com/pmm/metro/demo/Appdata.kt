package com.pmm.metro.demo

import com.orhanobut.logger.Logger
import com.pmm.metro.Metro
import com.pmm.metro.MetroMap
import com.pmm.metro.TransferStation
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

        MetroMap.addTransferStation(object : TransferStation {
            override fun transfer(path: String): String {
                Logger.d("目的站=${path}")
                return path
            }

        })
        MetroMap.addStation("/index", MainActivity::class.java)

    }
}