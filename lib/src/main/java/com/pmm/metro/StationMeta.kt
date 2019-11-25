package com.pmm.metro

import com.pmm.metro.annotatoin.StationType

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