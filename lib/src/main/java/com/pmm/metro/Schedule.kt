package com.pmm.metro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import java.io.Serializable

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 21:20
 * Description:
 */
class Schedule(private var ticket: Ticket, private val train: Train) {

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

    fun addTransferStation(list: List<TransferStation>) = this.apply {
        ticket.addTransferStation(list)
    }

    fun addTransferStation(transferStation: TransferStation) = this.apply {
        ticket.addTransferStation(arrayListOf(transferStation))
    }

    fun overridePendingTransition(enterAnim: Int, exitAnim: Int) = this.apply {
        ticket.enterAnim = enterAnim
        ticket.exitAnim = exitAnim
    }


    //执行
    fun go(requestCode: Int = -1) {
        //全局 中转站
        for (item in MetroMap.getTransferStation()) {
            ticket = item.transfer(ticket)
        }

        for (item in ticket.transferStations) {
            ticket = item.transfer(ticket)
        }

        val station = MetroMap.findStation(ticket.path)//查询车站
        val intent = ticket.intent
        val enterAnim = ticket.enterAnim
        val exitAnim = ticket.exitAnim

        when (train.target) {
            is FragmentActivity -> {
                val activity = train.target
                if (requestCode != -1) {
                    activity.startActivityForResult(intent.setClass(activity, station.destination), requestCode)
                } else {
                    activity.startActivity(intent.setClass(activity, station.destination))
                }
                if (enterAnim != 0 || exitAnim != 0)
                    activity.overridePendingTransition(enterAnim, exitAnim)
            }
            is Fragment -> {
                val fragment = train.target
                if (requestCode != -1) {
                    fragment.startActivityForResult(
                        intent.setClass(fragment.requireContext(), station.destination),
                        requestCode
                    )
                } else {
                    fragment.startActivity(intent.setClass(fragment.requireContext(), station.destination))
                }
            }
            is Context -> {
                val context = train.target
                //context启动的Intent 不能带requestCode
                context.startActivity(intent.setClass(context, station.destination))
            }
        }
    }
}