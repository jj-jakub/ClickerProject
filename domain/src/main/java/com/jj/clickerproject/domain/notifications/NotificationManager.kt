package com.jj.clickerproject.domain.notifications

import android.content.Intent

interface NotificationManager {
    fun showPushNotification(
        title: String,
        body: String,
        intent: Intent,
    )
}