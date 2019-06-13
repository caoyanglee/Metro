package com.pmm.metro

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.weimu.universalview.ktx.toast
import java.io.Serializable

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 21:20
 * Description:
 */
class Dispatcher(private var ticket: Ticket, private val driver: Any) {

    fun attribute(name: String, value: Int) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Byte) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Char) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Long) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Float) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Short) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Double) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Boolean) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Bundle) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: String) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: IntArray) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: ByteArray) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: CharArray) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: LongArray) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: FloatArray) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: ShortArray) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: DoubleArray) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: BooleanArray) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: CharSequence) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Serializable) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Array<out String>) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Array<out Parcelable>) = this.apply {
        ticket.attribute(name, value)
    }

    fun attribute(name: String, value: Array<out CharSequence>) = this.apply {
        ticket.attribute(name, value)
    }

    fun attributeIntList(name: String, value: ArrayList<Int>) = this.apply {
        ticket.attributeIntList(name, value)
    }

    fun attributeCharSequenceList(name: String, value: ArrayList<CharSequence>) = this.apply {
        ticket.attributeCharSequenceList(name, value)
    }

    fun attributeParcelableList(name: String, value: ArrayList<Parcelable>) = this.apply {
        ticket.attributeParcelableList(name, value)
    }

    fun attributeStringList(name: String, value: ArrayList<String>) = this.apply {
        ticket.attributeStringList(name, value)
    }

    fun attribute(intent: Intent) = this.apply {
        ticket.attribute(intent)
    }

    fun attribute(intent: Bundle) = this.apply {
        ticket.attribute(intent)
    }

    fun addTransfer(list: List<Transfer>) = this.apply {
        ticket.addTransferStation(list)
    }

    fun addTransfer(transfer: Transfer) = this.apply {
        ticket.addTransferStation(arrayListOf(transfer))
    }

    fun overridePendingTransition(enterAnim: Int, exitAnim: Int) = this.apply {
        ticket.enterAnim = enterAnim
        ticket.exitAnim = exitAnim
    }


    //获取站点
    fun getStation(): StationMeta? {
        //全局 中转站
        for (item in MetroMap.getTransfer()) {
            ticket = item.transfer(ticket)
        }

        for (item in ticket.transferStations) {
            ticket = item.transfer(ticket)
        }

        val station = MetroMap.findStation(ticket.path)//查询车站
        if (station == null) {
            toast("路径 = ${ticket.path} 无匹配结果！")
            return null
        }
        return station
    }

    //获取票
    fun getTicket() = ticket

    //获取驱动着
    fun getDriver() = driver

    //开启Activity
    fun go(requestCode: Int = -1) = direct2Activity().go(requestCode)

    //转换Activity
    fun direct2Activity() = ActivityLauncher(this)

    //转换Service
    fun direct2Service() = ServiceLauncher(this)

}