package com.pmm.metro

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.view.View

/**
 * Author:你需要一台永动机
 * Date:2019-06-13 18:20
 * Description:
 */
class FragmentLauncher(private val dispatcher: Dispatcher) {


    fun go(requestCode: Int = -1) {
        val station = dispatcher.getStation() ?: return


        val ticket = dispatcher.getTicket()
        val intent = ticket.intent
        val enterAnim = ticket.enterAnim
        val exitAnim = ticket.exitAnim

        SingleFragmentActivity.fragment = (station.destination.newInstance() as Fragment).apply {
            arguments = intent.extras
        }

        when (val driver = dispatcher.getDriver()) {
            is Activity -> {
                if (requestCode != -1) {
                    driver.startActivityForResult(
                        intent.setClass(driver, SingleFragmentActivity::class.java),
                        requestCode
                    )
                } else {
                    driver.startActivity(intent.setClass(driver, SingleFragmentActivity::class.java))
                }
                if (enterAnim != 0 || exitAnim != 0)
                    driver.overridePendingTransition(enterAnim, exitAnim)
            }
            is Fragment -> {
                if (requestCode != -1) {
                    driver.startActivityForResult(
                        intent.setClass(driver.requireContext(), SingleFragmentActivity::class.java),
                        requestCode
                    )
                } else {
                    driver.startActivity(intent.setClass(driver.requireContext(), SingleFragmentActivity::class.java))
                }
            }
            is Context -> {
                //context启动的Intent 不能带requestCode
                driver.startActivity(intent.setClass(driver, SingleFragmentActivity::class.java).apply {
                    this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
            is View -> {
                val context = driver.context
                //context启动的Intent 不能带requestCode
                context.startActivity(intent.setClass(context, SingleFragmentActivity::class.java).apply {
                    this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        }
    }
}