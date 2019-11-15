package com.pmm.metro.demo.transfer

import android.util.Log
import com.pmm.metro.Ticket
import com.pmm.metro.transfer.Transfer

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 16:51
 * Description:
 */
class UserCheckTransfer : Transfer {
    private val isLogin = false


    override fun transfer(chain: Transfer.Chain): Ticket {
        val ticket = chain.ticket()
        if (!isLogin) {
            Log.d("metro", "原始站=${ticket.path}")
            ticket.path = "/b"
            Log.d("metro", "未登录 中转到登录站=${ticket.path}")
        }
        return chain.proceed(ticket)
    }

}