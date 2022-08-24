package wot.core.lib.eventbus_ktx

import android.app.Application

object Comm {
    private lateinit var application: Application

    fun setContext(app: Application) {
        application = app
    }

    fun getApplication(): Application = application
}