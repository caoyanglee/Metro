package com.pmm.metro

import com.pmm.metro.transfer.Transfer

/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:35
 * Description:
 */
object MetroMap {

    private val stations = HashMap<String, StationMeta>()//车站
    private val transfers = arrayListOf<Transfer>()//中转站集合

    //查询车站
    @Throws(IllegalArgumentException::class)
    fun findStation(path: String): StationMeta {
        return stations[path] ?: throw IllegalArgumentException("the path $path not be founded!")
    }

    //查询车站
    @Throws(IllegalArgumentException::class)
    fun findStation(path: String, type: StationType): StationMeta {
        val station = stations[path] ?: throw IllegalArgumentException("the path $path not be founded!")
        if (station.type != type) throw IllegalArgumentException("path ${path} is no the $type type")
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
        for (item in stations) {
            modifyStation(item)
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

    //获取中转站
    fun getTransfers(): List<Transfer> = transfers

}