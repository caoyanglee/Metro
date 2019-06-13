package com.pmm.metro.demo

import com.orhanobut.logger.Logger
import com.pmm.metro.Ticket
import com.pmm.metro.Transfer

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 16:51
 * Description:
 */
class UserCheckTransferStation : Transfer {
    private val isLogin = false


    override fun transfer(ticket: Ticket): Ticket {
        var newTicket = ticket
        if (!isLogin) {
            newTicket = Ticket("/b")
            Logger.d("未登录 中转到登录站=${newTicket.path}")
        }
        return newTicket
    }

}