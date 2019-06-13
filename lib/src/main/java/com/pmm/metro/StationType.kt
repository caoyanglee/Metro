package com.pmm.metro

/**
 * Author:你需要一台永动机
 * Date:2019-05-17 02:20
 * Description:
 */
enum class StationType(var code: Int, var className: String) {
    ACTIVITY(0, "activity"),
    SERVICE(1, "service"),
    FRAGMENT(2, "fragment")

}