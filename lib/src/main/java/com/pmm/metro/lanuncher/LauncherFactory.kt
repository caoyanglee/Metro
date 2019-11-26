package com.pmm.metro.lanuncher

import com.pmm.metro.StationMeta
import com.pmm.metro.StationType
import com.pmm.metro.Ticket

/**
 * Author:你需要一台永动机
 * Date:2019-11-13 11:50
 * Description:简单工厂模式
 */
object LauncherFactory {

    //静态创建方法
    fun create(
        type: StationType,
        station: StationMeta?,
        ticket: Ticket,
        driver: Any
    ): AbstractLauncher {
        return when (type) {
            StationType.ACTIVITY -> ActivityLauncher(station, ticket, driver)
            StationType.SERVICE -> ServiceLauncher(station, ticket, driver)
            StationType.FRAGMENT -> FragmentLauncher(station, ticket, driver)
        }
    }
}