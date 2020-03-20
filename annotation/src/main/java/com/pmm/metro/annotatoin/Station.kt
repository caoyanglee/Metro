package com.pmm.metro.annotatoin

/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:35
 * Description:
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)//不设置默认为RunTime,非RunTime无法动态获取注解数据
@MustBeDocumented
annotation class Station(
    val path: String = ""
)