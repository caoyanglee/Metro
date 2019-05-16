# Metro
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![](https://jitpack.io/v/caoyanglee/Metro.svg)](https://jitpack.io/#caoyanglee/Metro)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
<br>
🚇地铁 一款简单的路由库

## 用法
**添加站点**
注解方式
```kotlin
//在对应的类上增加注解
@Station("/main")
class MainActivity{}
```

代码方式
```kotin
MetroMap.addStation("/main", MainActivity::class.java)
```


**跳转**
```kotlin
Metro.with(this)
    .path("/main")
    .attribute("currIndex", 1)
    .go()
```

**初始化**
```kotlin  
//在Application中
Metro.init(this)
```
**拦截器**
```kotlin
//增加中转站（类似拦截器）
MetroMap.addTransferStation(object : TransferStation {
    override fun transfer(ticket: Ticket): Ticket {
        Logger.d("目的路径 = ${ticket.path}")
        return ticket
    }
})
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
