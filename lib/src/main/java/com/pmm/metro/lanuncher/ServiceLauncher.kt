package com.pmm.metro.lanuncher

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.pmm.metro.StationMeta
import com.pmm.metro.Ticket

/**
 * Author:你需要一台永动机
 * Date:2019-06-13 17:49
 * Description: 启动Service
 */
class ServiceLauncher(
    station: StationMeta?,
    ticket: Ticket,
    driver: Any
) : AbstractLauncher(station, ticket, driver) {

    //开启Service 后台
    fun go() {
        if (station == null) return
        val intent = ticket.intent
        when (driver) {
            is Activity -> {
                driver.startService(intent.setClass(driver, station.destination))
            }
            is Fragment -> {
                val target = driver.requireActivity()
                target.startService(intent.setClass(target, station.destination))
            }
            is Context -> {
                driver.startService(intent.setClass(driver, station.destination))
            }
        }
    }

    //绑定Service
    fun bind(
        conn: ServiceConnection,
        flags: Int = Context.BIND_AUTO_CREATE
    ) {
        if (station == null) return
        val intent = ticket.intent
        when (driver) {
            is Activity -> {
                driver.bindService(intent.setClass(driver, station.destination), conn, flags)
            }
            is Fragment -> {
                val target = driver.requireActivity()
                target.bindService(intent.setClass(target, station.destination), conn, flags)
            }
            is Context -> {
                driver.bindService(intent.setClass(driver, station.destination), conn, flags)
            }
        }
    }

    //开启Service 前台
    @RequiresApi(Build.VERSION_CODES.O)
    fun goForeground() {
        if (station == null) return
        val intent = ticket.intent
        when (driver) {
            is Activity -> {
                driver.startForegroundService(intent.setClass(driver, station.destination))
            }
            is Fragment -> {
                val target = driver.requireActivity()
                target.startForegroundService(intent.setClass(target, station.destination))
            }
            is Context -> {
                driver.startForegroundService(intent.setClass(driver, station.destination))
            }
        }
    }

    //生成Intent
    fun generateIntent(): Intent? {
        if (station == null) return null
        val intent = ticket.intent

        when (driver) {
            is Activity -> intent.setClass(driver, station.destination)
            is Fragment -> intent.setClass(driver.requireActivity(), station.destination)
            is Context -> intent.setClass(driver, station.destination)
        }
        return intent
    }
}