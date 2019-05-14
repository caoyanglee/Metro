# Metro
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![](https://jitpack.io/v/caoyanglee/Metro.svg)](https://jitpack.io/#caoyanglee/Metro)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
<br>
🚇地铁 一款简单的路由库

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

## 用法
**在Application初始化**
```kotlin  
Metro.init(this)
```
**在Application初始化**
```kotlin
Metro.with(this)
    .path("/login")
    .attribute("name", "你需要一台永动机")
    .go()
```
