package com.jj.clickerproject.domain.click

import com.jj.clickerproject.domain.click.model.ScreenClickEvent

interface ClickManager {
    fun start()
    fun onEventReceived(screenClickEvent: ScreenClickEvent)
    fun onServiceConnected()
    fun stop()
    fun isAccessibilityPermissionGranted(): Boolean
}