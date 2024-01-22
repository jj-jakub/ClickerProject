package com.jj.clickerproject.core.di

import com.jj.clickerproject.core.data.google.GetGoogleDataUseCase
import com.jj.clickerproject.core.data.google.GetGoogleStatusUseCase
import com.jj.clickerproject.core.data.notifications.AndroidNotificationManager
import com.jj.clickerproject.domain.notifications.NotificationManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { GetGoogleStatusUseCase(clickerRepository = get()) }
    single { GetGoogleDataUseCase(clickerRepository = get()) }
    single<NotificationManager> { AndroidNotificationManager(context = androidContext()) }
}