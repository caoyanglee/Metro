package com.pmm.metro

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 16:37
 * Description:
 */
interface Transfer {

    fun transfer(ticket: Ticket): Ticket
}