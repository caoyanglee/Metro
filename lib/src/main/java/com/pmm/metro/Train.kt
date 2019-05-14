package com.pmm.metro

import android.support.v4.app.FragmentActivity
import com.pmm.metro.Station
import com.pmm.metro.Ticket

/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:32
 * Description:
 */
class Train(private val driver: Any) {

    fun path(path: String): Schedule = Schedule(Ticket(path), driver)

}



