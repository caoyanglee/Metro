package com.pmm.metro

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.pmm.metro.lanuncher.ActivityLauncher
import com.pmm.metro.lanuncher.FragmentLauncher
import com.pmm.metro.lanuncher.ServiceLauncher
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

    private fun doTransfer() {
        //全局 中转站
        for (item in MetroMap.getTransfers()) {
            ticket = item.transfer(ticket)
        }
        //局部 中转站
        for (item in transfers) {
            ticket = item.transfer(ticket)
        }
    }

    //获取站点
    private fun getStation(type: StationType): StationMeta? {
        //转转站
        doTransfer()
        //寻找站点
        var station: StationMeta? = null
        try {
            station = MetroMap.findStation(ticket.path)
            if (station.type != type) {
                throw IllegalArgumentException("path ${ticket.path} is no the $type type")
            }
        } catch (e: Exception) {
            this.failCallback?.invoke(e)
        }
        return station
    }

    //开启Activity
    fun go(requestCode: Int = -1) = activityLauncher().go()

    //转换Activity
    fun activityLauncher() = ActivityLauncher(getStation(StationType.ACTIVITY), ticket, driver)

    //转换Service
    fun serviceLauncher() = ServiceLauncher(getStation(StationType.SERVICE), ticket, driver)

    //转换Fragment
    fun fragmentLauncher() = FragmentLauncher(getStation(StationType.SERVICE), ticket, driver)

    //错误回调
    fun fail(failCallback: ((e: Exception) -> Unit)) = this.apply {
        this.failCallback = failCallback
    }
}