package com.jj.clickerproject.data.click

import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.jj.clickerproject.domain.click.ClickManager
import com.jj.clickerproject.domain.click.model.ScreenClickEvent
import com.jj.clickerproject.domain.click.usecase.state.IsAccessibilityAvailable
import com.jj.clickerproject.domain.click.usecase.state.SetAccessibilityClickAvailability
import com.jj.clickerproject.framework.accessibility.BACK_CLICK_INTENT_CATEGORY
import com.jj.clickerproject.framework.accessibility.CLICK_DURATION_PARAM
import com.jj.clickerproject.framework.accessibility.CLICK_INTENT_CATEGORY
import com.jj.clickerproject.framework.accessibility.HOME_CLICK_INTENT_CATEGORY
import com.jj.clickerproject.framework.accessibility.RECENT_APPS_CLICK_INTENT_CATEGORY
import com.jj.clickerproject.framework.accessibility.TouchAccessibilityService
import com.jj.clickerproject.framework.accessibility.X_COORDINATE_PARAM
import com.jj.clickerproject.framework.accessibility.Y_COORDINATE_PARAM
import kotlinx.coroutines.Job

class DefaultClickManager(
    private val context: Context,
    private val isAccessibilityAvailable: IsAccessibilityAvailable,
    private val setAccessibilityClickAvailability: SetAccessibilityClickAvailability,
) : ClickManager {

    private var eventObservingJob: Job? = null

    override fun start() {
        if (checkAccessibilityPermission()) {
            setAccessibilityClickAvailability(true)
        }
    }

    override fun onServiceConnected() {
        setAccessibilityClickAvailability(true)
    }

    override fun onEventReceived(screenClickEvent: ScreenClickEvent) {
        if (isAccessibilityAvailable()) {
            val serviceIntent = createServiceIntent(screenClickEvent)
            context.startService(serviceIntent)
        }
    }

    private fun createServiceIntent(screenClickEvent: ScreenClickEvent) =
        Intent(context, TouchAccessibilityService::class.java).apply {
            when (screenClickEvent) {
                ScreenClickEvent.BackClick -> {
                    addCategory(BACK_CLICK_INTENT_CATEGORY)
                }

                ScreenClickEvent.HomeClick -> {
                    addCategory(HOME_CLICK_INTENT_CATEGORY)
                }

                ScreenClickEvent.RecentAppsClick -> {
                    addCategory(RECENT_APPS_CLICK_INTENT_CATEGORY)
                }

                is ScreenClickEvent.Touch -> {
                    addCategory(CLICK_INTENT_CATEGORY)
                    putExtra(
                        X_COORDINATE_PARAM,
                        screenClickEvent.xClickCoordinate
                    )
                    putExtra(
                        Y_COORDINATE_PARAM,
                        screenClickEvent.yClickCoordinate
                    )
                    putExtra(
                        CLICK_DURATION_PARAM,
                        screenClickEvent.clickDuration
                    )
                }
            }
        }

    override fun stop() {
        setAccessibilityClickAvailability(false)
        eventObservingJob?.cancel()
        context.stopService(Intent(context, TouchAccessibilityService::class.java))
    }

    private fun checkAccessibilityPermission(): Boolean =
        try {
            val isAccessibilityAccessDenied = Settings.Secure.getInt(
                /* cr = */ context.contentResolver,
                /* name = */ Settings.Secure.ACCESSIBILITY_ENABLED,
            ) == 0
            if (isAccessibilityAccessDenied) {
                kotlin.runCatching {
                    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
                false
            } else {
                true
            }
        } catch (e: Exception) {
            false
        }

    override fun isAccessibilityPermissionGranted(): Boolean =
        try {
            val isAccessibilityAccessDenied = Settings.Secure.getInt(
                /* cr = */ context.contentResolver,
                /* name = */ Settings.Secure.ACCESSIBILITY_ENABLED,
            ) == 0
            !isAccessibilityAccessDenied
        } catch (e: Exception) {
            false
        }
}