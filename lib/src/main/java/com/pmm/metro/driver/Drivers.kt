package com.pmm.metro.driver

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 15:12
 * Description:
 */
class ActivityDriver(val target: FragmentActivity) : Driver

class FragmentDriver(val target: Fragment) : Driver

class ContextDriver(val target: Context) : Driver