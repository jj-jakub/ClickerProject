package com.jj.clickerproject.framework.accessibility

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.accessibilityservice.GestureDescription.StrokeDescription
import android.content.Intent
import android.graphics.Path
import android.view.accessibility.AccessibilityEvent
import com.jj.clickerproject.domain.click.ClickManager
import org.koin.android.ext.android.inject

const val CLICK_INTENT_CATEGORY = "CLICK_INTENT_CATEGORY"
const val BACK_CLICK_INTENT_CATEGORY = "BACK_CLICK_INTENT_CATEGORY"
const val HOME_CLICK_INTENT_CATEGORY = "HOME_CLICK_INTENT_CATEGORY"
const val RECENT_APPS_CLICK_INTENT_CATEGORY = "RECENT_APPS_CLICK_INTENT_CATEGORY"
const val STOP_SERVICE_CATEGORY = "STOP_SERVICE_CATEGORY"
const val X_COORDINATE_PARAM = "X_COORDINATE_PARAM"
const val Y_COORDINATE_PARAM = "Y_COORDINATE_PARAM"
const val CLICK_DURATION_PARAM = "CLICK_DURATION_PARAM"

class TouchAccessibilityService : AccessibilityService() {

    private val clickManager: ClickManager by inject()

    override fun onInterrupt() {}

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent ?: return START_STICKY
        when {
            intent.hasCategory(CLICK_INTENT_CATEGORY) -> {
                val x = intent.getFloatExtra(X_COORDINATE_PARAM, 0F)
                val y = intent.getFloatExtra(Y_COORDINATE_PARAM, 0F)
                val duration = intent.getLongExtra(CLICK_DURATION_PARAM, 1L)
                performClick(
                    x = x,
                    y = y,
                    duration = duration,
                )
            }

            intent.hasCategory(BACK_CLICK_INTENT_CATEGORY) -> performBackClick()
            intent.hasCategory(HOME_CLICK_INTENT_CATEGORY) -> performHomeClick()
            intent.hasCategory(RECENT_APPS_CLICK_INTENT_CATEGORY) -> performRecentAppsClick()
            intent.hasCategory(STOP_SERVICE_CATEGORY) -> disableSelf()
        }
        return START_STICKY
    }

    private fun performClick(x: Float, y: Float, duration: Long) {
        val clickPath = Path()
        clickPath.moveTo(x, y)
        val clickStroke = StrokeDescription(clickPath, 0, duration)
        val clickBuilder = GestureDescription.Builder().addStroke(clickStroke)
        dispatchGesture(clickBuilder.build(), null, null)
    }

    private fun performBackClick() {
        performGlobalAction(GLOBAL_ACTION_BACK)
    }

    private fun performHomeClick() {
        performGlobalAction(GLOBAL_ACTION_HOME)
    }

    private fun performRecentAppsClick() {
        performGlobalAction(GLOBAL_ACTION_RECENTS)
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        clickManager.onServiceConnected()
    }

    override fun onDestroy() {
        clickManager.stop()
        disableSelf()
        super.onDestroy()
    }
}