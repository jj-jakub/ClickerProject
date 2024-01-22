package com.jj.clickerproject.di.koin

import android.content.Context
import com.jj.clickerproject.core.di.coreModule
import com.jj.clickerproject.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinLauncher {

    fun startKoin(applicationContext: Context) {
        startKoin {
            androidContext(applicationContext)
            modules(mainModule, networkingModule, coreModule)
        }
    }
}
