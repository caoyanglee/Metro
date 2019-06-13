package com.pmm.metro

/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:35
 * Description:
 */
object MetroMap {

    private val stations = HashMap<String, StationMeta>()//车站
    private val transfers = arrayListOf<Transfer>()//中转站集合

    //查询车站
    fun findStation(path: String): StationMeta? {
        //return stations[path] ?: throw IllegalArgumentException("the path $path not be founded!")
        return stations[path]
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
    fun addStation(station: StationMeta) {
        modifyStation(station)
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
    fun getTransfer(): List<Transfer> {
        return transfers
    }

}