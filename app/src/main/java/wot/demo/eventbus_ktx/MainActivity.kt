package wot.demo.eventbus_ktx

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import wot.core.lib.eventbus_ktx.observeEvent
import wot.core.lib.eventbus_ktx.postEvent
import wot.demo.eventbus_ktx.event.GlobalEvent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeEvent<GlobalEvent>(isSticky = true) {
            Log.i("xxa", "${it.name} MainActivity#GlobalEvent粘性")
        }

        observeEvent<GlobalEvent> {
            Log.i("xxa", "${it.name} MainActivity#GlobalEvent非粘性")
        }
    }

    fun sendGlobalEvent(view: View) {
        Log.w("xxa", "MainActivity#GlobalEvent->")
        postEvent(GlobalEvent("MainActivity#GlobalEvent->"))
    }

    fun startGlobalEventActivity(view: View) {
        Log.w("xxa", "跳转->事件页面")
        startActivity(Intent(this, EventActivity::class.java))
    }
}