package com.pmm.metro.lanuncher

import android.app.Activity
import android.content.Context
import android.content.ServiceConnection
import android.view.View
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

    //开启Service
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
            is View -> {
                val target = driver.context
                target.startService(intent.setClass(target, station.destination))
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
            is View -> {
                val target = driver.context
                target.bindService(intent.setClass(target, station.destination), conn, flags)
            }
        }
    }
}