package com.jj.clickerproject.presentation

import android.app.Application
import com.jj.clickerproject.di.ActivityProvider
import com.jj.clickerproject.di.koin.KoinLauncher
import com.jj.clickerproject.domain.ad.AdManager
import org.koin.android.ext.android.inject

class ClickerProjectApplication : Application() {

    private val koinLauncher = KoinLauncher()

    private val activityProvider: ActivityProvider by inject()
    private val adManager: AdManager by inject()

    override fun onCreate() {
        super.onCreate()
        koinLauncher.startKoin(this)
        activityProvider.start()
        adManager.initAds()
    }
}