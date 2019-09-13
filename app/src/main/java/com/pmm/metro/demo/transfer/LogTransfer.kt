package com.pmm.metro.demo.transfer

import android.util.Log
import com.pmm.metro.Ticket
import com.pmm.metro.Transfer

/**
 * Author:你需要一台永动机
 * Date:2019-05-17 01:54
 * Description:
 */
class LogTransfer : Transfer {

    override fun transfer(ticket: Ticket): Ticket {
        Log.d("metro", "目的站=${ticket.path} intent=${ticket.intent.extras}")
        return ticket
    }
}