package wot.core.lib.eventbus_ktx

import android.app.Application

object EventBusLifeCycle {

    private lateinit var app: Application

    fun init(app: Application) {
        this.app = app
    }

    fun getApp(): Application = app
}