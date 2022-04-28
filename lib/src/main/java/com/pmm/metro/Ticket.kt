package com.pmm.metro

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 15:04
 * Description:
 */
class Ticket(path: String) {

    var intent: Intent = Intent()
        private set
    var enterAnim = 0//进入动画
    var exitAnim = 0//退出动画

    var path: String = ""
        set(value) {
            if (value.indexOf("?") != -1) {
                //将url的参数映射到intent当中
                val index = value.indexOf("?")
                field = value.substring(0, index)

                val params = value.substring(index + 1)
                val parasList = params.split("&")
                for (item in parasList) {
                    val keyValue = item.split("=")
                    put(keyValue[0], keyValue[1])
                }
            } else {
                field = value
            }
        }

    init {
        this.path = path
    }

    fun put(name: String, value: Int) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Byte) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Char) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Long) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Float) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Short) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Double) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Boolean) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Bundle) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: String) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: IntArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: ByteArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: CharArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: LongArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: FloatArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: ShortArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: DoubleArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: BooleanArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: CharSequence) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Serializable) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Array<out String>) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Array<out Parcelable>) = this.apply {
        intent.putExtra(name, value)
    }

    fun put(name: String, value: Array<out CharSequence>) = this.apply {
        intent.putExtra(name, value)
    }

    fun putIntList(name: String, value: ArrayList<Int>) = this.apply {
        intent.putIntegerArrayListExtra(name, value)
    }

    fun putCharSequenceList(name: String, value: ArrayList<CharSequence>) = this.apply {
        intent.putCharSequenceArrayListExtra(name, value)
    }

    fun putParcelableList(name: String, value: ArrayList<Parcelable>) = this.apply {
        intent.putParcelableArrayListExtra(name, value)
    }

    fun putStringList(name: String, value: ArrayList<String>) = this.apply {
        intent.putStringArrayListExtra(name, value)
    }

    fun put(intent: Intent) = this.apply {
        this.intent.putExtras(intent)
    }

    fun put(bundle: Bundle) = this.apply {
        this.intent.putExtras(bundle)
    }

    private fun clearParams() = this.apply {
        this.intent.extras?.clear()
    }

    fun overridePendingTransition(enterAnim: Int, exitAnim: Int) = this.apply {
        this.enterAnim = enterAnim
        this.exitAnim = exitAnim
    }

    fun addFlags(flags: Int) = this.apply {
        this.intent.addFlags(flags)
    }

    fun addCategory(category: String) = this.apply {
        this.intent.addCategory(category)
    }

    private fun clearOverridePendingTransition() = this.apply {
        this.enterAnim = 0
        this.exitAnim = 0
    }

    fun clear() {
        this.path = ""
        clearParams()
        clearOverridePendingTransition()
    }
}