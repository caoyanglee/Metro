# Metro
[![License](https://img.shields.io/npm/l/mithril.svg)](https://www.npmjs.com/package/mithril)
[![](https://jitpack.io/v/caoyanglee/Metro.svg)](https://jitpack.io/#caoyanglee/Metro)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
<br>
🚇地铁 一款简单的Kotlin路由库

## 用法

**初始化**
```kotlin  
//在Application中
Metro.init(this)
```

**添加站点** 
> 注意：站点现支持Activity，Service，Fragment

1. 注解方式
```kotlin
//在对应的类上增加注解
@Station("/main")
class MainActivity{}
```

2. 代码方式
```kotin
MetroMap.addStation("/main", MainActivity::class.java)
```

**跳转**
```kotlin
//跳转到Activity
Metro.with(this)
    .path("/main")
    .put("currIndex", 1)
    .go()
    
//跳转到Service
Metro.with(this)
    .path("/main")
    .serviceLauncher()
    .go()

//跳转到Fragment
Metro.with(this)
    .path("/main")
    .fragmentLauncher()
    .go()
```

**错误回调**
```kotlin 
Metro.with(this)
    .path("/main")
    .fail {
        Log.e("metro", it.toString())
    }
    .go()
```

**拦截器**
```kotlin
//增加中转站（类似拦截器）
MetroMap.addTransferStation(object : Transfer {

    override fun run(chain: Transfer.Chain): Ticket {
        val ticket = chain.ticket()
        Logger.d("目的路径 = ${ticket.path}")
        return chain.proceed(ticket)
    }
})
```
```kotlin
//登录拦截器
class UserAuthTransfer : Transfer {

    override fun transfer(chain: Transfer.Chain): Ticket {
        val ticket = chain.ticket()
        if (needLogin(ticket.path)) {
            ticket.clear()
            ticket.path = "/account/login"
            ticket.overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.noting)
        }
        return chain.proceed(ticket)
    }

    private fun needLogin(path: String) = when {
        path.isBlank() -> false
        //增加需要校验登录权限的页面
        path.startsWith("/main") -> true
        else -> false
    }

}
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
**具体模块的build.gradle，例如app.gradle**
> 注意：默认使用kotlin1.3.60版本的库

[![](https://jitpack.io/v/caoyanglee/Metro.svg)](https://jitpack.io/#caoyanglee/Metro)

```gradle
//Kapt插件，必须！
apply plugin: 'kotlin-kapt'

//生成的路由配置类为UUID命名，若要让生成路由配置类明确模块，可加入以下操作
kapt {
    arguments {
        arg("metroModuleName", project.getName())
    }
}

//常规依赖
dependencies {
    //库本体只要在底层模块依赖一次即可
    implementation 'com.github.caoyanglee.Metro:lib:{latestVersion}'

    //在需要路由功能的模块下，使用注解处理器
    kapt 'com.github.caoyanglee.Metro:compiler:{latestVersion}'
}

```

## 混淆
```
#Metro
-keep class com.pmm.metro.**{*;}
-keepnames class com.pmm.metro.**{*;}
```

## 优化建议
在Application中的`Metro.init(this)`，默认会加载所有路由配置文件，因为是自动扫描所以是耗时操作，我们建议做以下优化

```kotlin  
Metro.init(
    context = this,
    autoLoadConfigClass = false //不在使用自动加载
    )
//指定加载路由配置文件，速度快
Metro.loadConfigClass("MetroRoute_xxx")
```

PS:路由配置文件生成的位置在各个moduel的`build/generated/source/kaptKotlin/debug`中，每个配置类对应的包名都是`com.pmm.metro.route`,唯一不同的是类名。
