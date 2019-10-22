package com.pmm.metro.lanuncher

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.pmm.metro.*

/**
 * Author:你需要一台永动机
 * Date:2019-06-13 17:46
 * Description: 启动Activity
 */
class ActivityLauncher(
    station: StationMeta?,
    ticket: Ticket,
    driver: Any
) : AbstractLauncher(station, ticket, driver) {

    fun go(requestCode: Int = -1) {
        if (station == null) return
        val intent = ticket.intent
        val enterAnim = ticket.enterAnim
        val exitAnim = ticket.exitAnim

        when (driver) {
            is Activity -> {
                if (requestCode != -1) {
                    driver.startActivityForResult(
                        intent.setClass(driver, station.destination),
                        requestCode
                    )
                } else {
                    driver.startActivity(intent.setClass(driver, station.destination))
                }
                if (enterAnim != 0 || exitAnim != 0)
                    driver.overridePendingTransition(enterAnim, exitAnim)
            }
            is Fragment -> {
                if (requestCode != -1) {
                    driver.startActivityForResult(
                        intent.setClass(driver.requireContext(), station.destination),
                        requestCode
                    )
                } else {
                    driver.startActivity(
                        intent.setClass(
                            driver.requireContext(),
                            station.destination
                        )
                    )
                }
            }
            is Context -> {
                //context启动的Intent 不能带requestCode
                driver.startActivity(intent.setClass(driver, station.destination).apply {
                    this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
            is View -> {
                val context = driver.context
                //context启动的Intent 不能带requestCode
                context.startActivity(intent.setClass(context, station.destination).apply {
                    this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        }
    }
}