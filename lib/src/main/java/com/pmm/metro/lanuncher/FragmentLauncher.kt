package com.pmm.metro.lanuncher

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.pmm.metro.StationMeta
import com.pmm.metro.Ticket
import com.pmm.metro.ui.FragmentAy

/**
 * Author:你需要一台永动机
 * Date:2019-06-13 18:20
 * Description: 启动Fragment
 */
class FragmentLauncher(
    station: StationMeta?,
    ticket: Ticket,
    driver: Any
) : AbstractLauncher(station, ticket, driver) {

    fun go(requestCode: Int = -1) {
        if (station == null) return
        val intent = ticket.intent
        val enterAnim = ticket.enterAnim
        val exitAnim = ticket.exitAnim

        FragmentAy.fragment = (station.destination.newInstance() as Fragment).apply {
            arguments = intent.extras
        }

        when (driver) {
            is Activity -> {
                if (requestCode != -1) {
                    driver.startActivityForResult(
                        intent.setClass(driver, FragmentAy::class.java),
                        requestCode
                    )
                } else {
                    driver.startActivity(intent.setClass(driver, FragmentAy::class.java))
                }
                if (enterAnim != 0 || exitAnim != 0)
                    driver.overridePendingTransition(enterAnim, exitAnim)
            }
            is Fragment -> {
                if (requestCode != -1) {
                    driver.startActivityForResult(
                        intent.setClass(driver.requireContext(), FragmentAy::class.java),
                        requestCode
                    )
                } else {
                    driver.startActivity(
                        intent.setClass(
                            driver.requireContext(),
                            FragmentAy::class.java
                        )
                    )
                }
            }
            is Context -> {
                //context启动的Intent 不能带requestCode
                driver.startActivity(intent.setClass(driver, FragmentAy::class.java).apply {
                    this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        }
    }
}