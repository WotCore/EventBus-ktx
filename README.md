[![](https://jitpack.io/v/yangsanning/EventBus-ktx.svg)](https://jitpack.io/#yangsanning/EventBus-ktx)
[![API](https://img.shields.io/badge/API-21%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=21)

# EventBus-ktx
> [重点类说明](https://github.com/yangsanning/EventBus-ktx/wiki)

## 使用
#### 订阅
```kotlin
// 订阅全局粘性
observeEvent<GlobalEvent>(isSticky = true) {
}
// 订阅全局非粘性
observeEvent<GlobalEvent> {
}

// 订阅activity粘性
observeEvent<ActivityEvent>(owner = activity as ComponentActivity, isSticky = true) {
}
// 订阅activity非粘性
observeEvent<ActivityEvent>(owner = activity as ComponentActivity) {
}

// 订阅fragment粘性
observeEvent<FragmentEvent>(owner = this@EventFragment, isSticky = true) {      
}

// 订阅fragment非粘性
observeEvent<FragmentEvent>(owner = this@EventFragment) {
}

```

#### 发送
```kotlin
// 范围: 全局
postEvent(GlobalEvent("全局"))

// 范围: activity
postEvent(activity, ActivityEvent("activity"))

// 范围: fragment
postEvent(this@EventFragment, FragmentEvent("fragment"))
```

## 引入

#### 添加仓库

在项目的 `build.gradle` 文件中配置仓库地址。

```android
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

#### 添加项目依赖

在需要添加依赖的 Module 下添加以下信息，使用方式和普通的远程仓库一样。

```android
implementation 'com.github.yangsanning:EventBus-ktx:1.0.0'
```
