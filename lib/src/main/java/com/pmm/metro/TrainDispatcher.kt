package com.pmm.metro

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.pmm.metro.lanuncher.ActivityLauncher
import com.pmm.metro.lanuncher.FragmentLauncher
import com.pmm.metro.lanuncher.LauncherFactory
import com.pmm.metro.lanuncher.ServiceLauncher
import com.pmm.metro.transfer.LogTransfer
import com.pmm.metro.transfer.Transfer
import com.pmm.metro.transfer.TransferChain
import java.io.Serializable
import java.lang.Exception

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 21:20
 * Description:
 */
class TrainDispatcher(private var ticket: Ticket, private val driver: Any) {

    private val transfers = arrayListOf<Transfer>()//中转站集合

    private var failCallback: ((e: Exception) -> Unit)? = null

    fun put(name: String, value: Int) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Byte) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Char) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Long) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Float) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Short) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Double) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Boolean) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Bundle) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: String) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: IntArray) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: ByteArray) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: CharArray) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: LongArray) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: FloatArray) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: ShortArray) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: DoubleArray) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: BooleanArray) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: CharSequence) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Serializable) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Array<out String>) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Array<out Parcelable>) = this.apply {
        ticket.put(name, value)
    }

    fun put(name: String, value: Array<out CharSequence>) = this.apply {
        ticket.put(name, value)
    }

    fun putIntList(name: String, value: ArrayList<Int>) = this.apply {
        ticket.putIntList(name, value)
    }

    fun putCharSequenceList(name: String, value: ArrayList<CharSequence>) = this.apply {
        ticket.putCharSequenceList(name, value)
    }

    fun putParcelableList(name: String, value: ArrayList<Parcelable>) = this.apply {
        ticket.putParcelableList(name, value)
    }

    fun putStringList(name: String, value: ArrayList<String>) = this.apply {
        ticket.putStringList(name, value)
    }

    fun put(intent: Intent) = this.apply {
        ticket.put(intent)
    }

    fun put(bundle: Bundle) = this.apply {
        ticket.put(bundle)
    }

    fun addTransfer(list: List<Transfer>) = this.apply {
        transfers.addAll(list)
    }

    fun addTransfer(transfer: Transfer) = this.apply {
        transfers.add(transfer)
    }

    fun overridePendingTransition(enterAnim: Int, exitAnim: Int) = this.apply {
        ticket.overridePendingTransition(enterAnim, exitAnim)
    }

    fun addFlags(flags: Int) = this.apply {
        ticket.addFlags(flags)
    }

    fun singleTop() = this.apply {
        ticket.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }

    fun addCategory(category: String) = this.apply {
        ticket.addCategory(category)
    }

    //获取中转处理后的票
    private fun getTicketAfterTransfer(): Ticket {
        val transfers = arrayListOf<Transfer>()
        transfers.addAll(this.transfers)//局部 中转站
        transfers.addAll(MetroMap.getTransfers())//全局 中转站
        transfers.add(LogTransfer())//基础 中转站

        if (transfers.isNotEmpty()) {
            val chain: Transfer.Chain = TransferChain(transfers, 0, ticket)
            ticket = chain.proceed(ticket)
        }
        return ticket
    }

    //获取站点
    private fun findStation(type: StationType): StationMeta? {
        //获取票
        val ticket = getTicketAfterTransfer()
        //寻找站点
        var station: StationMeta? = null
        try {
            station = MetroMap.findStation(ticket.path, type)
        } catch (e: Exception) {
            this.failCallback?.invoke(e)
        }
        return station
    }

    //开启Activity
    fun go(requestCode: Int = -1, options: Bundle? = null) =
        activityLauncher().go(requestCode, options)

    //转换Activity
    fun activityLauncher(): ActivityLauncher {
        val type = StationType.ACTIVITY
        val station = findStation(type)
        return LauncherFactory.create(type, station, ticket, driver) as ActivityLauncher
    }

    //转换Service
    fun serviceLauncher(): ServiceLauncher {
        val type = StationType.SERVICE
        val station = findStation(type)
        return LauncherFactory.create(type, station, ticket, driver) as ServiceLauncher
    }

    //转换Fragment
    fun fragmentLauncher(): FragmentLauncher {
        val type = StationType.FRAGMENT
        val station = findStation(type)
        return LauncherFactory.create(type, station, ticket, driver) as FragmentLauncher
    }

    //错误回调
    fun fail(failCallback: ((e: Exception) -> Unit)) = this.apply {
        this.failCallback = failCallback
    }

    //获取Intent
    fun getIntent() = ticket.intent
}