package com.pmm.metro.demo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.pmm.metro.Station

/**
 * Author:你需要一台永动机
 * Date:2019-06-13 15:27
 * Description:
 */
@Station("/service/test")
class TestService : Service() {
    private val mBinder by lazy { TestBinder() }

    override fun onBind(intent: Intent?): IBinder? {
        logd("TestService onBind")
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        logd("TestService onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logd("TestService onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    class TestBinder : Binder() {
        fun getName() = "你需要一台永动机"
    }

    override fun onDestroy() {
        super.onDestroy()
        logd("TestService onDestroy")
    }
}