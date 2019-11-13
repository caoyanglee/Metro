package com.pmm.metro.transfer

import android.util.Log
import com.pmm.metro.BuildConfig
import com.pmm.metro.Metro
import com.pmm.metro.Ticket

/**
 * Author:你需要一台永动机
 * Date:2019-11-13 11:30
 * Description:
 */
class LogTransfer : Transfer {

    override fun transfer(chain: Transfer.Chain): Ticket {
        val ticket = chain.ticket()
        if (Metro.enableLog)
            Log.d("metro", "station=${ticket.path} intent=${ticket.intent.extras}")
        return ticket
    }
}