package com.pmm.metro.demo

import com.orhanobut.logger.Logger
import com.pmm.metro.TransferStation

/**
 * Author:你需要一台永动机
 * Date:2019-05-14 16:51
 * Description:
 */
class LoginTransferStation : TransferStation {
    private val isLogin = false

    override fun transfer(path: String): String {
        var newPath = path
        if (!isLogin) {
            newPath = "/b"
            Logger.d("未登录 中转到登录站=${newPath}")
        }
        return newPath
    }
}