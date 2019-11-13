package com.pmm.metro.lanuncher

import com.pmm.metro.MetroMap
import com.pmm.metro.StationMeta
import com.pmm.metro.StationType
import com.pmm.metro.Ticket
import java.lang.Exception

/**
 * Author:你需要一台永动机
 * Date:2019-11-13 11:50
 * Description:
 */
object LauncherFactory {

    //转换Activity
    fun activity(station: StationMeta?, ticket: Ticket, driver: Any) = ActivityLauncher(station, ticket, driver)

    //转换Service
    fun service(station: StationMeta?, ticket: Ticket, driver: Any) = ServiceLauncher(station, ticket, driver)

    //转换Fragment
    fun fragment(station: StationMeta?, ticket: Ticket, driver: Any) = FragmentLauncher(station, ticket, driver)
}