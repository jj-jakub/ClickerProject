package com.jj.clickerproject.data.firebase

import android.content.Intent
import android.net.Uri
import com.google.firebase.messaging.RemoteMessage
import com.jj.clickerproject.core.data.notifications.model.PushMessageKeys.APP_PACKAGE_NAME_KEY

private const val MARKET_APP_URI_STRING = "market://details?id="

fun getAppPackageName(remoteMessage: RemoteMessage) =
    if (remoteMessage.data.containsKey(APP_PACKAGE_NAME_KEY)) {
        remoteMessage.data[APP_PACKAGE_NAME_KEY]
    } else {
        null
    }

fun getAppPageOpeningIntent(appPackageName: String) =
    Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_APP_URI_STRING + appPackageName))