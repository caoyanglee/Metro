package com.pmm.metro

/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:35
 * Description:
 */
object MetroMap {

    private val stations = HashMap<String, StationEntity>()//车站
    private val transferStations = arrayListOf<TransferStation>()//中转站集合

    //查询车站
    fun findStation(path: String): StationEntity? {
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

    //修改车站
    fun modifyStation(
        path: String,
        destination: Class<*>,
        type: StationType = StationType.ACTIVITY
    ) {
        val station = StationEntity(path, destination, type)
        stations[path] = station
    }

    //删除车站
    fun removeStation(path: String) {
        stations.remove(path)
    }


    //增加中转站
    fun addTransferStation(transferStation: TransferStation) {
        transferStations.add(transferStation)
    }

    //获取中转站
    fun getTransferStation(): List<TransferStation> {
        return transferStations
    }

}