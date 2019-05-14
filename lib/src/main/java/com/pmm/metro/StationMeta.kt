package com.pmm.metro

/**
 * Author:你需要一台永动机
 * Date:2019-05-13 23:04
 * Description:
 */
class StationMeta(
    var path: String,
    var destination: Class<*>,
    var type: StationType = StationType.ACTIVITY
)

enum class StationType(var code: Int, var className: String) {
    ACTIVITY(0, "activity"),
    SERVICE(1, "service")

}