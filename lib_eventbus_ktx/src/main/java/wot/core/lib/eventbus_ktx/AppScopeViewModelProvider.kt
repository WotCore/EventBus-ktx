package wot.core.lib.eventbus_ktx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * @Sub EventBus
 * @Description App 范围提供者
 * @Author Wot.Yang
 * @CreateDate 2022/8/16
 * @Organization: Wot
 */
object AppScopeViewModelProvider : ViewModelStoreOwner {

    private val eventViewModelStore: ViewModelStore = ViewModelStore()

    override fun getViewModelStore(): ViewModelStore {
        return eventViewModelStore
    }

    private val applicationProvider: ViewModelProvider by lazy {
        ViewModelProvider(
            AppScopeViewModelProvider,
            ViewModelProvider.AndroidViewModelFactory.getInstance(EventBusLifeCycle.getApp())
        )
    }

    fun <T : ViewModel> getAppScopeViewModel(modelClass: Class<T>): T {
        return applicationProvider[modelClass]
    }
}