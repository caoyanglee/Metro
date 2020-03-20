package com.pmm.metro

/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:32
 * Description:
 */
class Train(private val driver: Any) {

    fun path(path: String) = TrainDispatcher(Ticket(path), driver)

    fun path(ticket: Ticket) = TrainDispatcher(ticket, driver)

}



