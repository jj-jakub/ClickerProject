package com.jj.clickerproject.di.koin

import com.jj.clickerproject.BuildConfig
import com.jj.clickerproject.data.ad.DefaultAdManager
import com.jj.clickerproject.data.ad.GetInterstitialAdUnitId
import com.jj.clickerproject.data.ad.GetMainAdUnitId
import com.jj.clickerproject.data.app.DefaultAppInfoRepository
import com.jj.clickerproject.data.app.GetIsInstalledFromValidSource
import com.jj.clickerproject.data.config.AppConfiguration
import com.jj.clickerproject.data.config.VersionTextProvider
import com.jj.clickerproject.data.network.RetrofitFactory
import com.jj.clickerproject.di.ActivityProvider
import com.jj.clickerproject.domain.ad.AdManager
import com.jj.clickerproject.domain.app.AppInfoRepository
import com.jj.clickerproject.presentation.MainRootViewModel
import com.jj.clickerproject.presentation.ui.main.MainScreenViewModel
import com.jj.clickerproject.presentation.ui.secondary.SecondaryScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single {
        AppConfiguration(
            baseUrl = BuildConfig.ServerBaseUrl,
        )
    }
    single { RetrofitFactory() }
    single {
        get<RetrofitFactory>().retrofit(
            baseUrl = get<AppConfiguration>().baseUrl,
        )
    }
    single { VersionTextProvider() }

    viewModel {
        MainScreenViewModel(
            versionTextProvider = get(),
            adManager = get(),
            getGoogleStatusUseCase = get(),
            getGoogleDataUseCase = get(),
            getIsInstalledFromValidSource = get(),
        )
    }
    viewModel {
        MainRootViewModel(
            getMainAdUnitId = get(),
        )
    }
    viewModel {
        SecondaryScreenViewModel(savedStateHandle = get())
    }
    single<AdManager> {
        DefaultAdManager(
            context = androidContext(),
            activityProvider = get(),
            getInterstitialAdUnitId = get(),
        )
    }
    single { ActivityProvider(application = androidApplication()) }
    single { GetMainAdUnitId() }
    single { GetInterstitialAdUnitId() }
    single<AppInfoRepository> { DefaultAppInfoRepository(context = androidContext()) }
    single { GetIsInstalledFromValidSource(appInfoRepository = get()) }
}