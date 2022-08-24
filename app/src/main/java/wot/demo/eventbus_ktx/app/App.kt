package wot.demo.eventbus_ktx.app

import android.app.Application
import wot.core.lib.eventbus_ktx.Comm

/**
 * @Sub 附属
 * @Description 作用
 * @Author Wot.Yang
 * @CreateDate 2022/8/24
 * @Organization: Wot
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Comm.setContext(this)
    }
}