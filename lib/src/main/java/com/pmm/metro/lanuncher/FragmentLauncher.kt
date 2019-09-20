package com.pmm.metro.lanuncher

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.pmm.metro.Dispatcher
import com.pmm.metro.StationType
import com.pmm.metro.ui.FragmentAy

/**
 * Author:你需要一台永动机
 * Date:2019-06-13 18:20
 * Description:
 */
class FragmentLauncher(private val dispatcher: Dispatcher) {


    fun go(requestCode: Int = -1) {
        val station = dispatcher.getStation(StationType.FRAGMENT) ?: return


        val ticket = dispatcher.getTicket()
        val intent = ticket.intent
        val enterAnim = ticket.enterAnim
        val exitAnim = ticket.exitAnim

        FragmentAy.fragment = (station.destination.newInstance() as Fragment).apply {
            arguments = intent.extras
        }

        when (val driver = dispatcher.getDriver()) {
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
                    driver.startActivity(intent.setClass(driver.requireContext(), FragmentAy::class.java))
                }
            }
            is Context -> {
                //context启动的Intent 不能带requestCode
                driver.startActivity(intent.setClass(driver, FragmentAy::class.java).apply {
                    this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
            is View -> {
                val context = driver.context
                //context启动的Intent 不能带requestCode
                context.startActivity(intent.setClass(context, FragmentAy::class.java).apply {
                    this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        }
    }
}