package com.pmm.metro.transfer

import com.pmm.metro.Ticket

/**
 * Author:你需要一台永动机
 * Date:2019-11-13 11:04
 * Description:
 */
class TransferChain(var transfers: List<Transfer>, var index: Int, var ticket: Ticket) : Transfer.Chain {

    override fun ticket(): Ticket = ticket

    override fun proceed(ticket: Ticket): Ticket {
        if (index >= transfers.size) throw AssertionError()

        val next: Transfer.Chain = TransferChain(transfers, index + 1, ticket)
        val transfer = transfers[index]
        return transfer.transfer(next)
    }
}