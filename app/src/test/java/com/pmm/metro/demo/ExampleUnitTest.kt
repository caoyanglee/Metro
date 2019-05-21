package com.pmm.metro.demo

import android.util.Log
import com.orhanobut.logger.Logger
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)

        val url = "www.baidu.com?a=1&b=2"

        val index =url.indexOf("?")

        print("index= $index")

        val newUrl = url.substring(0,index)

        print("\nnewUrl= $newUrl")

        val params = url.substring(index+1)

        print("\nparams= $params")

        val parasList = params.split("&")
        for (item in parasList){
            print("\nparam= $item")

            val key = item.split("=")[0]
            val value = item.split("=")[1]

            print("\nkey= $key")
            print("\nvalue= $value")
        }



    }
}
