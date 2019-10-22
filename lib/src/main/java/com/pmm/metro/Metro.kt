package com.pmm.metro

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import dalvik.system.DexFile
import dalvik.system.PathClassLoader
import java.io.IOException


/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:28
 * Description:
 */
object Metro {

    fun with(target: Any): Train {
        if (target !is Activity && (target !is Fragment) && (target !is Context) && (target !is View))
            throw IllegalAccessException("target must be Activity,Fragment,Context or View")
        return Train(target)
    }

    fun init(context: Application, needSanStations: Boolean = true) {
        if (needSanStations) {
            //扫描所有带Station的类
            val map = scan(context)
            MetroMap.addStation(*map.toTypedArray())
        }

        //增加全局 中转站
//        MetroMap.addTransfer(object : TransferStation {
//            override fun transfer(path: String): String {
//                Logger.d("目的站=${path}")
//                return path
//            }
//        })
    }

    /**
     * 扫描所有代理有@Staion的类，并返回Map集合
     */
    private fun scan(context: Context): List<StationMeta> {
        val stationsList = arrayListOf<StationMeta>()
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

                        val type = when {
                            Activity::class.java.isAssignableFrom(entryClass) -> StationType.ACTIVITY
                            Service::class.java.isAssignableFrom(entryClass) -> StationType.SERVICE
                            Fragment::class.java.isAssignableFrom(entryClass) -> StationType.FRAGMENT
                            else -> StationType.ACTIVITY
                        }
                        stationsList.add(
                            StationMeta(
                                path = station.path,
                                destination = entryClass,
                                type = type
                            )
                        )
                    }
                }
            }
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return stationsList
    }


}