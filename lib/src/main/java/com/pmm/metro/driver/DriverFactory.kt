package com.pmm.metro.driver

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.pmm.metro.driver.Driver
import java.lang.IllegalArgumentException

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 14:49
 * Description:
 */
class DriverFactory {

    fun getDriver(target: Any): Driver = when {
        target is FragmentActivity -> ActivityDriver(target)
        target is Fragment -> FragmentDriver(target)
        target is Context -> ContextDriver(target)
        else -> throw IllegalArgumentException("illegal target ! we support FragmentActivity,Fragment or Context")
    }
}