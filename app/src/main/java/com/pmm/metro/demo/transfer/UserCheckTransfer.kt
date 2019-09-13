package com.pmm.metro.demo.transfer

import android.util.Log
import com.orhanobut.logger.Logger
import com.pmm.metro.Ticket
import com.pmm.metro.Transfer

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 16:51
 * Description:
 */
class UserCheckTransfer : Transfer {
    private val isLogin = false


    override fun transfer(ticket: Ticket): Ticket {
        if (!isLogin) {
            ticket.path = "/b"
            Log.d("metro","未登录 中转到登录站=${ticket.path}")
        }
        return ticket
    }

}