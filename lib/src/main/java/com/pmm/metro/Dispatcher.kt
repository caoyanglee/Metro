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
class Dispatcher(private var ticket: Ticket, private val driver: Any) {

    private val transfers = arrayListOf<Transfer>()//中转站集合

    private var failCallback: ((e: Exception) -> Unit)? = null

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Int) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Byte) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Char) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Long) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Float) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Short) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Double) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Boolean) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Bundle) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: String) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: IntArray) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: ByteArray) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: CharArray) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: LongArray) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: FloatArray) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: ShortArray) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: DoubleArray) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: BooleanArray) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: CharSequence) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Serializable) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Array<out String>) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Array<out Parcelable>) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(name: String, value: Array<out CharSequence>) = this.apply {
        ticket.put(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attributeIntList(name: String, value: ArrayList<Int>) = this.apply {
        ticket.putIntList(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attributeCharSequenceList(name: String, value: ArrayList<CharSequence>) = this.apply {
        ticket.putCharSequenceList(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attributeParcelableList(name: String, value: ArrayList<Parcelable>) = this.apply {
        ticket.putParcelableList(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attributeStringList(name: String, value: ArrayList<String>) = this.apply {
        ticket.putStringList(name, value)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(intent: Intent) = this.apply {
        ticket.put(intent)
    }

    @Deprecated("请使用put方法 Please use put function")
    fun attribute(intent: Bundle) = this.apply {
        ticket.put(intent)
    }


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

    //获取站点
    fun getStation(type: StationType): StationMeta? {
        //全局 中转站
        for (item in MetroMap.getTransfers()) {
            ticket = item.transfer(ticket)
        }
        //局部 中转站
        for (item in transfers) {
            ticket = item.transfer(ticket)
        }
        val station = MetroMap.findStation(ticket.path)//查询车站
        if (station == null) {
            failCallback?.invoke(IllegalArgumentException("Route = ${ticket.path} is no Found！"))
            return null
        }
        if (station.type != type) {
            failCallback?.invoke(IllegalArgumentException("Route = ${ticket.path} is no the ${type} type"))
            return null
        }
        return station
    }

    //获取票
    fun getTicket() = ticket

    //获取驱动者
    fun getDriver() = driver

    //开启Activity
    fun go(requestCode: Int = -1) = activityLauncher().go(requestCode)

    //转换Activity
    fun activityLauncher() = ActivityLauncher(this)

    //转换Service
    fun serviceLauncher() = ServiceLauncher(this)

    //转换Fragment
    fun fragmentLauncher() = FragmentLauncher(this)

    //错误回调
    fun fail(failCallback: ((e: Exception) -> Unit)) = this.apply {
        this.failCallback = failCallback
    }
}