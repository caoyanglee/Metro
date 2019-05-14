package com.pmm.metro

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.pmm.metro.MetroMap
import com.pmm.metro.driver.ActivityDriver
import com.pmm.metro.driver.ContextDriver
import com.pmm.metro.driver.Driver
import com.pmm.metro.driver.FragmentDriver
import java.io.Serializable

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 15:04
 * Description:
 */
class Ticket(private var path: String, private val driver: Driver) {

    private val Intent: Intent by lazy { Intent() }
    private var transferStation: TransferStation? = null//中转站


    fun attribute(name: String, value: Int) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Byte) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Char) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Long) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Float) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Short) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Double) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Boolean) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Bundle) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: String) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: IntArray) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: ByteArray) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: CharArray) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: LongArray) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: FloatArray) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: ShortArray) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: DoubleArray) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: BooleanArray) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: CharSequence) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Serializable) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Array<out String>) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Array<out Parcelable>) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Array<out CharSequence>) = this.apply {
        Intent.putExtra(name, value)
    }

    fun attributeIntList(name: String, value: ArrayList<Int>) = this.apply {
        Intent.putIntegerArrayListExtra(name, value)
    }

    fun attributeCharSequenceList(name: String, value: ArrayList<CharSequence>) = this.apply {
        Intent.putCharSequenceArrayListExtra(name, value)
    }

    fun attributeParcelableList(name: String, value: ArrayList<Parcelable>) = this.apply {
        Intent.putParcelableArrayListExtra(name, value)
    }

    fun attributeStringList(name: String, value: ArrayList<String>) = this.apply {
        Intent.putStringArrayListExtra(name, value)
    }

    fun attribute(intent: Intent) = this.apply {
        Intent.putExtras(intent)
    }

    fun attribute(intent: Bundle) = this.apply {
        Intent.putExtras(intent)
    }

    fun transfer(transferStation: TransferStation) = this.apply {
        this.transferStation = transferStation
    }

    //执行
    fun go(requestCode: Int = -1) {
        //做拦截操作 path识别切换

        //全局 中转站
        for (item in MetroMap.getTransferStation()) {
            path = item.transfer(path)
        }

        //自己 中转站
        if (transferStation != null)
            path = transferStation!!.transfer(path)

        if (path.isBlank()) return //path为空时不做操作

        val station = MetroMap.findStation(path)
        when (driver) {
            is ActivityDriver -> {
                val activity = driver.target
                if (requestCode != -1) {
                    activity.startActivityForResult(Intent.setClass(activity, station.destination), requestCode)
                } else {
                    activity.startActivity(Intent.setClass(activity, station.destination))
                }
            }
            is FragmentDriver -> {
                val fragment = driver.target
                if (requestCode != -1) {
                    fragment.startActivityForResult(
                        Intent.setClass(fragment.requireContext(), station.destination),
                        requestCode
                    )
                } else {
                    fragment.startActivity(Intent.setClass(fragment.requireContext(), station.destination))
                }
            }
            is ContextDriver -> {
                val context = driver.target
                //context启动的Intent 不能带requestCode
                context.startActivity(Intent.setClass(context, station.destination))
            }
        }
    }
}