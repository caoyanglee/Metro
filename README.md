# Metro
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![](https://jitpack.io/v/caoyanglee/Metro.svg)](https://jitpack.io/#caoyanglee/Metro)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
<br>
🚇地铁 一款简单的路由库

## 用法
**跳转**
```kotlin
Metro.with(this)
    .path("/login")
    .attribute("name", "你需要一台永动机")
    .go()
```

**初始化 **
```kotlin  
//在Application中
Metro.init(this)
```
**配置**
```kotlin
//增加中转站（类似拦截器）
MetroMap.addTransferStation(object : TransferStation {
    override fun transfer(ticket: Ticket): Ticket {
        Logger.d("目的站 = ${ticket.path}")
        return ticket
    }
})

//代码方式 增加站点
MetroMap.addStation("/index", MainActivity::class.java)
```

## 获取依赖

**project的build.gradle**

```
allprojects {
    repositories {
        ......       
        maven { url "https://jitpack.io" } 
    }
}
```
**app的build.gradle**
[![](https://jitpack.io/v/caoyanglee/Metro.svg)](https://jitpack.io/#caoyanglee/Metro)

```gradle

implementation 'com.github.caoyanglee:Metro:{latestVersion}'

```
