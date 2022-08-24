package wot.core.lib.eventbus_ktx

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 监听[App范围]事件
 * @param dispatcher 协程调度器 线程切换
 * @param minState 指定可感知的最小生命状态
 * @param isSticky 是否给新的订阅者发送之前的数据
 */
inline fun <reified T> LifecycleOwner.observeEvent(
    dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    minState: Lifecycle.State = Lifecycle.State.STARTED,
    isSticky: Boolean = false,
    noinline onReceived: (T) -> Unit,
) {
    AppScopeViewModelProvider.getAppScopeViewModel(EventBusCore::class.java)
        .observeEvent(
            this,
            T::class.java.name,
            minState,
            dispatcher,
            isSticky,
            onReceived
        )
}

/**
 * 监听[Fragment范围]事件
 * @param dispatcher 协程调度器 线程切换
 * @param minState 指定可感知的最小生命状态
 * @param isSticky 是否给新的订阅者发送之前的数据
 */
inline fun <reified T> observeEvent(
    owner: Fragment,
    dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    minState: Lifecycle.State = Lifecycle.State.STARTED,
    isSticky: Boolean = false,
    noinline onReceived: (T) -> Unit,
) {
    ViewModelProvider(owner).get(EventBusCore::class.java)
        .observeEvent(owner, T::class.java.name, minState, dispatcher, isSticky, onReceived)
}

/**
 * Fragment 监听[Activity范围]事件
 * @param dispatcher 协程调度器 线程切换
 * @param minState 指定可感知的最小生命状态
 * @param isSticky 是否给新的订阅者发送之前的数据
 */
inline fun <reified T> observeEvent(
    owner: ComponentActivity,
    dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    minState: Lifecycle.State = Lifecycle.State.STARTED,
    isSticky: Boolean = false,
    noinline onReceived: (T) -> Unit,
) {
    ViewModelProvider(owner).get(EventBusCore::class.java)
        .observeEvent(owner, T::class.java.name, minState, dispatcher, isSticky, onReceived)
}

/**
 * 监听[App范围]无感知生命周期事件
 * @param isSticky 是否给新的订阅者发送之前的数据
 */
inline fun <reified T> observeEvent(
    coroutineScope: CoroutineScope,
    isSticky: Boolean = false,
    noinline onReceived: (T) -> Unit,
) {
    coroutineScope.launch {
        AppScopeViewModelProvider.getAppScopeViewModel(EventBusCore::class.java)
            .observeWithoutLifecycle(T::class.java.name, isSticky, onReceived)
    }
}

/**
 * 监听[限定范围]无感知生命周期事件
 * @param isSticky 是否给新的订阅者发送之前的数据
 */
inline fun <reified T> observeEvent(
    owner: ViewModelStoreOwner,
    coroutineScope: CoroutineScope,
    isSticky: Boolean = false,
    noinline onReceived: (T) -> Unit,
) {
    coroutineScope.launch {
        ViewModelProvider(owner).get(EventBusCore::class.java)
            .observeWithoutLifecycle(T::class.java.name, isSticky, onReceived)
    }
}

/**
 * 发送[App范围]的事件
 */
inline fun <reified T> postEvent(event: T, timeMillis: Long = 0L) {
    AppScopeViewModelProvider.getAppScopeViewModel(EventBusCore::class.java)
        .postEvent(T::class.java.name, event!!, timeMillis)
}

/**
 * 发送[限定范围]的事件
 */
inline fun <reified T> postEvent(owner: ViewModelStoreOwner, event: T, timeMillis: Long = 0L) {
    ViewModelProvider(owner).get(EventBusCore::class.java)
        .postEvent(T::class.java.name, event!!, timeMillis)
}

/**
 * 移除[App范围]事件
 */
inline fun <reified T> removeStickyEvent(event: Class<T>) {
    AppScopeViewModelProvider.getAppScopeViewModel(EventBusCore::class.java)
        .removeStickEvent(event.name)
}

/**
 * 移除[限定范围]事件
 */
inline fun <reified T> removeStickyEvent(owner: ViewModelStoreOwner, event: Class<T>) {
    ViewModelProvider(owner).get(EventBusCore::class.java)
        .removeStickEvent(event.name)
}

/**
 * 获取[App范围]的事件数量
 */
inline fun <reified T> getEventObserverCount(event: Class<T>): Int {
    return AppScopeViewModelProvider.getAppScopeViewModel(EventBusCore::class.java)
        .getEventObserverCount(event.name)
}

/**
 * 获取[限定范围]的事件数量
 */
inline fun <reified T> getEventObserverCount(owner: ViewModelStoreOwner, event: Class<T>): Int {
    return ViewModelProvider(owner).get(EventBusCore::class.java)
        .getEventObserverCount(event.name)
}

/**
 * 清除[App范围]事件缓存
 */
inline fun <reified T> clearStickyEvent(event: Class<T>) {
    AppScopeViewModelProvider.getAppScopeViewModel(EventBusCore::class.java)
        .clearStickEvent(event.name)
}

/**
 * 清除[限定范围]事件缓存
 */
inline fun <reified T> clearStickyEvent(owner: ViewModelStoreOwner, event: Class<T>) {
    ViewModelProvider(owner).get(EventBusCore::class.java)
        .clearStickEvent(event.name)
}

fun <T> LifecycleOwner.launchWhenStateAtLeast(minState: Lifecycle.State, block: suspend CoroutineScope.() -> T) {
    lifecycleScope.launch {
        lifecycle.whenStateAtLeast(minState, block)
    }
}
