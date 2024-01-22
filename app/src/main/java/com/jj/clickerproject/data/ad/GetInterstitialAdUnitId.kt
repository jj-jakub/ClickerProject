package com.jj.clickerproject.data.ad

import com.jj.clickerproject.BuildConfig

class GetInterstitialAdUnitId {
    operator fun invoke(): String = BuildConfig.adInterstitialAdUnitId
}