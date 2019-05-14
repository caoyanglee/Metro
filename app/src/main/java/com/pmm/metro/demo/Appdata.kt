package com.pmm.metro.demo

import com.pmm.metro.Metro
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
    }
}