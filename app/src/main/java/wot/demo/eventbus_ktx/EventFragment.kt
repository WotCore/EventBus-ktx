package wot.demo.eventbus_ktx

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import wot.core.lib.eventbus_ktx.observeEvent
import wot.core.lib.eventbus_ktx.postEvent
import wot.demo.eventbus_ktx.event.ActivityEvent
import wot.demo.eventbus_ktx.event.FragmentEvent
import wot.demo.eventbus_ktx.event.GlobalEvent

/**
 * @Sub 附属
 * @Description 事件 Fragment
 * @Author Wot.Yang
 * @CreateDate 2022/8/24
 * @Organization: Wot
 */
class EventFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.event_fragment, null)
        v.initView()
        return v
    }

    private fun View.initView() {
        findViewById<Button>(R.id.sendGlobalEvent).setOnClickListener(this@EventFragment)
        findViewById<Button>(R.id.sendActivityEvent).setOnClickListener(this@EventFragment)
        findViewById<Button>(R.id.sendFragmentEvent).setOnClickListener(this@EventFragment)

        observeEvent<GlobalEvent>(isSticky = true) {
            Log.i("xxa", "${it.name} ActivityFragment#GlobalEvent粘性")
        }

        observeEvent<GlobalEvent> {
            Log.i("xxa", "${it.name} ActivityFragment#GlobalEvent非粘性")
        }

        observeEvent<ActivityEvent>(owner = activity as ComponentActivity, isSticky = true) {
            Log.i("xxa", "${it.name} ActivityFragment#ActivityEvent粘性")
        }

        observeEvent<ActivityEvent>(owner = activity as ComponentActivity) {
            Log.i("xxa", "${it.name} ActivityFragment#ActivityEvent非粘性")
        }

        observeEvent<FragmentEvent>(owner = this@EventFragment, isSticky = true) {
            Log.i("xxa", "${it.name} ActivityFragment#FragmentEvent粘性")
        }

        observeEvent<FragmentEvent>(owner = this@EventFragment) {
            Log.i("xxa", "${it.name} ActivityFragment#FragmentEvent非粘性")
        }
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.sendGlobalEvent -> {
                Log.w("xxa", "ActivityFragment#GlobalEvent->")
                postEvent(GlobalEvent("ActivityFragment#GlobalEvent->"))
            }
            R.id.sendActivityEvent -> {
                Log.w("xxa", "ActivityFragment#ActivityEvent->")

                postEvent(activity as ComponentActivity, ActivityEvent("ActivityFragment#ActivityEvent->"))
            }
            R.id.sendFragmentEvent -> {
                Log.w("xxa", "ActivityFragment#FragmentEvent->")
                postEvent(this@EventFragment, FragmentEvent("ActivityFragment#FragmentEvent->"))
            }
            else -> {}
        }
    }
}