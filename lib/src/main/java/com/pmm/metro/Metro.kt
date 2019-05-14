package com.pmm.metro

import android.app.Application
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.orhanobut.logger.Logger
import com.pmm.metro.driver.Driver
import com.pmm.metro.driver.DriverFactory
import dalvik.system.DexFile
import dalvik.system.PathClassLoader
import java.io.IOException
import java.util.HashMap


/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:28
 * Description:
 */
object Metro {

    private val driverFactory by lazy { DriverFactory() }//司机工厂


    fun with(target: FragmentActivity): Driver = driverFactory.getDriver(target)

    fun with(target: Fragment): Driver = driverFactory.getDriver(target)

    fun with(target: Context): Driver = driverFactory.getDriver(target)

    fun init(context: Application) {
        //扫描所有带Station的类
        val map = scan(context)
        for (item in map) {
            MetroMap.addStation(item.key, item.value)
        }

        //增加全局 中转站
//        MetroMap.addTransferStation(object : TransferStation {
//            override fun transfer(path: String): String {
//                Logger.d("目的站=${path}")
//                return path
//            }
//        })
    }


    private fun scan(context: Context): Map<String, Class<*>> {
        val map = HashMap<String, Class<*>>()

        try {
            val classLoader = Thread.currentThread().contextClassLoader as PathClassLoader

            val df = DexFile(context.packageCodePath)
            val n = df.entries()
            while (n.hasMoreElements()) {
                val element = n.nextElement()
                //扫所有的类
                val entryClass = df.loadClass(element, classLoader)
                if (entryClass != null) {

                    val annotation = entryClass.getAnnotation<Station>(Station::class.java)
                    if (annotation != null) {
                        val station = annotation as Station
                        //Logger.e("path=" + annotation.path());
                        map[station.path] = entryClass
                    }
                }
            }
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return map
    }


}