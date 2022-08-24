package wot.demo.eventbus_ktx

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import wot.core.lib.eventbus_ktx.observeEvent
import wot.core.lib.eventbus_ktx.postEvent
import wot.demo.eventbus_ktx.event.ActivityEvent
import wot.demo.eventbus_ktx.event.GlobalEvent

/**
 * @Sub 附属
 * @Description 事件 Activity
 * @Author Wot.Yang
 * @CreateDate 2022/8/24
 * @Organization: Wot
 */
class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_activity)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, EventFragment())
            .commitNowAllowingStateLoss()

        observeEvent<GlobalEvent>(isSticky = true) {
            Log.i("xxa", "${it.name} EventActivity#GlobalEvent粘性")
        }

        observeEvent<GlobalEvent> {
            Log.i("xxa", "${it.name} EventActivity#GlobalEvent非粘性")
        }

        observeEvent<ActivityEvent>(owner = this, isSticky = true) {
            Log.i("xxa", "${it.name} EventActivity#ActivityEvent粘性")
        }

        observeEvent<ActivityEvent>(owner = this) {
            Log.i("xxa", "${it.name} EventActivity#ActivityEvent非粘性")
        }
    }

    fun sendGlobalEvent(view: View) {
        Log.w("xxa", "EventActivity#GlobalEvent->")
        postEvent(GlobalEvent("EventActivity#GlobalEvent->"))
    }

    fun sendActivityEvent(view: View) {
        Log.w("xxa", "EventActivity#ActivityEvent->")
        postEvent(this, ActivityEvent("EventActivity#ActivityEvent->"))
    }
}