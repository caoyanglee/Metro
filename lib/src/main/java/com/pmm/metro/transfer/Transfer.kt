package com.pmm.metro.transfer

import com.pmm.metro.Ticket

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 16:37
 * Description:
 */
interface Transfer {

    fun run(chain: Chain): Ticket

    interface Chain {
        fun ticket(): Ticket
        fun proceed(ticket: Ticket): Ticket
    }
}