package com.pmm.metro

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.pmm.metro.annotatoin.Station
import com.pmm.metro.annotatoin.StationType
import dalvik.system.DexFile
import dalvik.system.PathClassLoader
import java.io.IOException


/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:28
 * Description:
 */
object Metro {

    private lateinit var context: Application

    /**
     * whether enable log or not,default true
     *
     * 是否开启日志，默认开启
     */
    var enableLog = true

    /**
     * use Activity
     */
    fun with(target: Activity) = Train(target)

    /**
     * use Context
     */
    fun with(target: Context) = Train(target)

    /**
     * use View
     */
    fun with(target: View) = Train(target.context)

    /**
     * use Fragment
     */
    fun with(target: Fragment) = Train(target)


    /**
     * init the lib
     *
     * 初始化库
     */
    fun init(context: Application) {
        this.context = context
//        if (needSanStations) {
//            val stations = scan(context)
//            MetroMap.addStation(stations)
//        }
        try {
            val clazz = Class.forName("com.pmm.metro.route.MetroRoute")
            val method = clazz.getMethod("loadRouter")
            method.invoke(null)
        } catch (e: Exception) {
            //nothing
        }
    }

    /**
     * get Context of Application
     *
     * 获取应用层级的上下文
     */
    fun getContext() = context

    /**
     * scan all class with @Station Annotation,
     * this method suit for small project,
     * if you are the big one,recommend use MetroMap.addStation() by hand
     *
     * 扫描所有代理有@Staion的类，并返回Map集合
     * 此方法适合小项目，类少！
     * 大项目建议使用MetroMap.addStation()手动添加
     */
    private fun scan(context: Context): List<StationMeta> {
        val stationsList = arrayListOf<StationMeta>()
        try {
            val classLoader = Thread.currentThread().contextClassLoader as PathClassLoader

            val df = DexFile(context.packageCodePath)
            val n = df.entries()
            var i = 0;
            while (n.hasMoreElements()) {
                i++
                val element = n.nextElement()
                //scan all classes 扫所有的类
                val entryClass = df.loadClass(element, classLoader) ?: continue
                val station = entryClass.getAnnotation(Station::class.java) ?: continue //reflect 反射
                //get type 获取类型
                val type = when {
                    Activity::class.java.isAssignableFrom(entryClass) -> StationType.ACTIVITY
                    Service::class.java.isAssignableFrom(entryClass) -> StationType.SERVICE
                    Fragment::class.java.isAssignableFrom(entryClass) -> StationType.FRAGMENT
                    else -> StationType.ACTIVITY
                }
                stationsList.add(
                    StationMeta(path = station.path, destination = entryClass, type = type)
                )
            }
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return stationsList
    }


}