package com.pmm.metro

/**
 * Author:你需要一台永动机
 * Date:2019-05-13 22:35
 * Description:
 */
@Target(AnnotationTarget.CLASS)
@MustBeDocumented
annotation class Station(
    val path: String = ""
)