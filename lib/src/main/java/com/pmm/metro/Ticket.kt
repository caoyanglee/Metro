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

    val intent: Intent by lazy { Intent() }
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
                    attribute(keyValue[0], keyValue[1])
                }
            } else {
                field = value
            }
        }

    init {
        this.path = path
    }

    fun attribute(name: String, value: Int) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Byte) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Char) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Long) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Float) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Short) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Double) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Boolean) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Bundle) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: String) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: IntArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: ByteArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: CharArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: LongArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: FloatArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: ShortArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: DoubleArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: BooleanArray) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: CharSequence) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Serializable) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Array<out String>) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Array<out Parcelable>) = this.apply {
        intent.putExtra(name, value)
    }

    fun attribute(name: String, value: Array<out CharSequence>) = this.apply {
        intent.putExtra(name, value)
    }

    fun attributeIntList(name: String, value: ArrayList<Int>) = this.apply {
        intent.putIntegerArrayListExtra(name, value)
    }

    fun attributeCharSequenceList(name: String, value: ArrayList<CharSequence>) = this.apply {
        intent.putCharSequenceArrayListExtra(name, value)
    }

    fun attributeParcelableList(name: String, value: ArrayList<Parcelable>) = this.apply {
        intent.putParcelableArrayListExtra(name, value)
    }

    fun attributeStringList(name: String, value: ArrayList<String>) = this.apply {
        intent.putStringArrayListExtra(name, value)
    }

    fun attribute(intent: Intent) = this.apply {
        this.intent.putExtras(intent)
    }

    fun attribute(intent: Bundle) = this.apply {
        this.intent.putExtras(intent)
    }

    fun clearAttributes() = this.apply {
        this.intent.extras?.clear()
    }

    fun overridePendingTransition(enterAnim: Int, exitAnim: Int) = this.apply {
        this.enterAnim = enterAnim
        this.exitAnim = exitAnim
    }

    fun clearOverridePendingTransition() = this.apply {
        this.enterAnim = 0
        this.exitAnim = 0
    }

    fun clear() {
        this.path = ""
        clearAttributes()
        clearOverridePendingTransition()
    }
}