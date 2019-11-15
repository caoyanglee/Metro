package com.pmm.metro

import com.pmm.metro.transfer.Transfer

/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:35
 * Description:
 */
object MetroMap {

    private val stations = HashMap<String, StationMeta>()//车站 哈希表
    private val transfers = arrayListOf<Transfer>()//中转站 集合

    //查询车站
    @Throws(IllegalStateException::class)
    fun findStation(path: String, type: StationType? = null): StationMeta {
        val station = checkNotNull(stations[path], lazyMessage = { "the path $path not be founded!\n路径找不到！" })
        if (type != null) check(station.type == type, lazyMessage = { "path $path is no the ${type.name} type\n路径${path}不是${type.name}类型" })
        return station
    }

    //增加车站
    fun addStation(
        path: String,
        destination: Class<*>,
        type: StationType = StationType.ACTIVITY
    ) {
        modifyStation(path, destination, type)
    }

    //增加车站
    fun addStation(vararg stations: StationMeta) {
        stations.forEach {
            modifyStation(it)
        }
    }

    //增加车站
    fun addStation(stations: List<StationMeta>) {
        stations.forEach {
            modifyStation(it)
        }
    }

    //修改车站
    fun modifyStation(
        path: String,
        destination: Class<*>,
        type: StationType = StationType.ACTIVITY
    ) {
        val station = StationMeta(path, destination, type)
        stations[path] = station
    }

    //修改车站
    fun modifyStation(station: StationMeta) {
        stations[station.path] = station
    }

    //删除车站
    fun removeStation(path: String) {
        stations.remove(path)
    }

    //增加中转站
    fun addTransfer(transfer: Transfer) {
        transfers.add(transfer)
    }

    //清除中转站
    fun clearTransfer() {
        transfers.clear()
    }

    //获取中转站
    fun getTransfers(): List<Transfer> = transfers

}