package com.pmm.metro.driver

import com.pmm.metro.Station
import com.pmm.metro.Ticket

/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:32
 * Description:
 */
interface Driver {

    fun path(path: String): Ticket = Ticket(path, this)

}



