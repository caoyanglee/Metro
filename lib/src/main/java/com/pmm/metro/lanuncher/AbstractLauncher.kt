package com.pmm.metro.lanuncher

import com.pmm.metro.StationMeta
import com.pmm.metro.Ticket

/**
 * Author:你需要一台永动机
 * Date:2019-10-22 15:10
 * Description:
 */
abstract class AbstractLauncher(
    protected val station: StationMeta?,
    protected val ticket: Ticket,
    protected val driver: Any
)