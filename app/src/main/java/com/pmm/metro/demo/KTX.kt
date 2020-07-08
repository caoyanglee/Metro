package com.pmm.metro.demo

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * Author:你需要一台永动机
 * Date:2019-06-13 15:32
 * Description:
 */

fun Any.logd(message: String) {
    Log.d("metro", message)
}

//吐司通知-普通
fun Context?.toast(message: CharSequence) {
    try {
        val targetContext = this ?: AppData.context
        val duration = if (message.length > 30) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        Toast.makeText(targetContext, message, duration).show()
    } catch (e: Exception) {
        //doNothing
    }
}

/**
 * 单击 单参
 */

inline fun View.click(crossinline click: ((View) -> Unit)) {
    this.click(click, 600)
}
/**
 * 单击 双参
 */
inline fun View.click(crossinline click: ((View) -> Unit), delay: Long = 600) {
    var isSingleClick = false//是否正在处理事件
    this.setOnClickListener {
        MainScope().launch {
            if (isSingleClick) return@launch
            click.invoke(this@click)
            isSingleClick = true
            kotlinx.coroutines.delay(delay)
            isSingleClick = false
        }
    }
}